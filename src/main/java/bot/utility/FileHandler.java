package bot.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileHandler {

    /**
     * Downloads a file to ./data/ folder
     * @param fileUrl download from
     * @param name to save file as
     * @throws IOException if connection fails
     */
    // FIXME: 24/03/2021 doesn't check if extension is correct
    public static void downloadFile(String fileUrl, String name) throws IOException {

        File file = new File("data/" + name);

        URL url = new URL(fileUrl);
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();

        InputStream inputStream = urlConnection.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(file);

        byte[] buffer = new byte[2048];
        int length;

        while((length = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, length);
        }

        inputStream.close();
        fileOutputStream.close();
    }

    /**
     * Adds the extension of a file from a URL to a file name
     * @param url of the file
     * @param fileName the name to be given to the file
     * @return filename + extension
     */
    // FIXME: 24/03/2021 might be no extension
    public static String getFullFileName(String url, String fileName) {

        String fileType = url.substring(url.lastIndexOf('.'));
        return fileName + fileType;
    }

    public static void deleteFile(String fileName) {

        File file = new File("data/" + fileName);
        if(file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Error occured while deleting file");
        }
    }
}
