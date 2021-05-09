package bot.command.hidden_commands;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;
import bot.command.commands.Command;
import bot.utility.FileHandler;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Transforms reddit link into either media, source link or text
 */
public class RedditSourceCommand extends Command {

    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private final String OAUTH_TOKEN_URL = "https://www.reddit.com/api/v1/access_token";
    private final String APP_ID = "eCERrYoUW4tVkQ";
    private final String APP_SECRET = "IhCbEx00NnJZYHGYh7IJkbFE_CKH-A";

    public static final String commandID = "REDDIT_SOURCE_INTERNAL";

    private String urlString;

    public RedditSourceCommand(String url) {
        urlString = url;
    }

    @Override
    public void execute() {

        try {
            JSONObject authToken = getToken();
            JSONObject postData = getInfo(authToken.get("access_token").toString());

            // If link doesn't exist
            if(postData.has("message") && postData.get("message").equals("Not Found")) {
                return;
            }

            // Remove irrelevant data
            postData = postData.getJSONObject("data").getJSONArray("children")
                    .getJSONObject(0).getJSONObject("data");

            executeUnknownType(postData);
            executeKnownType(postData);

        } catch (IOException e) {
            System.err.println("Could not recieve data from reddit");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("JSON element does not exist");
        }
    }

    /**
     * Transforms the url of posts with unknown type
     * @param postData JSON data of the url
     * @param interactor used to write to Messenger
     */
    private void executeUnknownType(JSONObject postData) {

        String fileURL = postData.has("url_overridden_by_dest") ?
                fileURL = postData.get("url_overridden_by_dest").toString() : "";

        if(!postData.has("post_hint")) {

            // Post is a gallery of images or a an unknown type with url
            if(postData.has("is_gallery") && postData.get("is_gallery").toString().equals("true") ||
                    !fileURL.equals("")) {

                Scheduler.scheduleSendText(fileURL);
            }

            // Text post
            else if(postData.has("is_self") && postData.get("is_self").toString().equals("true") &&
                    postData.has("selftext") && !postData.get("selftext").toString().equals("")) {

                Scheduler.scheduleSendText(postData.get("selftext").toString());
            }

            // The rest of the function checks post_hint, so skip it
            return;
        }
    }

    /**
     * Transforms the url of posts with known type
     * @param postData JSON data of the url
     * @param interactor used to write to Messenger
     */
    private void executeKnownType(JSONObject postData) throws IOException {

        String fileURL = postData.has("url_overridden_by_dest") ?
                fileURL = postData.get("url_overridden_by_dest").toString() : "";


        switch(postData.get("post_hint").toString()) {

            // Either image or gif
            case "image" :
                final String fileName = FileHandler.getFullFileName(fileURL, "redditSource");
                FileHandler.downloadFile(fileURL, fileName);
                Scheduler.scheduleSendMedia(fileName);
                // FIXME: 25/03/2021  Delete file once it is fully uploaded (check DOM)
                break;
            case "hosted:video" :
                String hostedUrl = postData.getJSONObject("secure_media")
                        .getJSONObject("reddit_video").get("fallback_url").toString();
                Scheduler.scheduleSendText(hostedUrl);
                break;
            case "rich:video" :
            case "link" :

                // Ignore redirects to reddit
                if(!fileURL.contains("v.redd")) {
                    Scheduler.scheduleSendText(fileURL);
                }
        }
    }

    /**
     * Acquires authentication token for api calls
     * @return JSON representation of the token
     * @throws IOException if connection unsuccessful
     */
    private JSONObject getToken() throws IOException {

        GenericUrl url = new GenericUrl(OAUTH_TOKEN_URL);
        Map<String, String> params = new HashMap<String, String>(3);
        params.put("grant_type", "client_credentials");
        HttpContent hc = new UrlEncodedContent(params);

        HttpRequestFactory requestFactory = HTTP_TRANSPORT
                .createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.getHeaders().setBasicAuthentication(APP_ID, APP_SECRET);
                    }
                });

        HttpRequest request = requestFactory.buildPostRequest(url, hc);
        HttpResponse response = request.execute();

        JSONObject token = null;
        try {
            if (response.isSuccessStatusCode()) {

                String json = response.parseAsString();

                JSONTokener tokener = null;
                tokener = new JSONTokener(json);
                token = new JSONObject(tokener);
            } else
                System.out.println("Request failed with " + response.getStatusCode());
        } finally {
            response.disconnect();
        }

        return token;
    }

    /**
     * Gets GET request to api
     * @param token authentication token
     * @return JSON object from the url
     * @throws IOException if connection unsuccessful
     */
    private JSONObject getInfo(String token) throws IOException {

        GenericUrl url = new GenericUrl(getPostUrl());

        HttpRequestFactory requestFactory = HTTP_TRANSPORT
                .createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        request.getHeaders().setAuthorization( "bearer " + token );
                        request.getHeaders().setUserAgent("pc:messenger-bot:v0.0.1 (by /u/Cupio_Dissolvi_)");
                    }
                });

        HttpRequest request = requestFactory.buildGetRequest(url);
        HttpResponse response = request.execute();

        JSONObject jsonObject = null;

        try {
            if (response.isSuccessStatusCode()) {

                String json = response.parseAsString();

                JSONTokener tokener = null;
                tokener = new JSONTokener(json);
                jsonObject = new JSONObject(tokener);

            } else
                System.out.println("Request failed with " + response.getStatusCode());
        } finally {
            response.disconnect();
        }

        return jsonObject;
    }

    /**
     * Constructs appropriate url for a post from reddit api
     * @return url
     */
    private String getPostUrl() {

        Pattern pattern = Pattern.compile("(http://www.|https://www.|http://|" +
                "https://)?(reddit|old.reddit)(.[a-z]+/r/[a-zA-Z]+/comments/)");
        Matcher matcher = pattern.matcher(urlString);
        String postID = "";

        if(matcher.find()) {
            String urlEnding = urlString.substring(matcher.end());
            postID = urlEnding.substring(0, urlEnding.indexOf('/'));
        }

        return "https://oauth.reddit.com/api/info/?id=t3_" + postID;
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID)
                .summary("Transforms reddit link into either media, source link or text.\nCannot be called directly");
    }
}
