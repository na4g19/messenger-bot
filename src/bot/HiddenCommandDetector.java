package bot;

import bot.commands.Command;
import bot.commands.hidden_commands.RedditSourceCommand;
import bot.commands.hidden_commands.YoutubeSetTimeCommand;
import bot.utility.RegexMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Some commands may not start with '!';
 * Some may be hidden within a message and are executed without
 * the user directly instructing so.
 * E.g. when sending URL containing .jpg, bot instead sends the image
 * This class handles such scenarios by returning the appropriate command found.
 */
// FIXME: 23/03/2021 Future issue: multiples commands may be found, either return the first or a list of commands
public class HiddenCommandDetector {

    private Command command = null;

    public HiddenCommandDetector(Message message) {

        String messageContent = message.getCommand();

        if(isRedditSource(messageContent)) {
            command = new RedditSourceCommand(messageContent);
        }
        else if(isYoutubeSetTime(messageContent)) {
            command = new YoutubeSetTimeCommand(messageContent);
        }
    }

    public boolean isExecutable() {
        return command != null;
    }

    public Command getCommand() {
        return command;
    }

    private boolean isRedditSource(String messageContent) {

        String pattern = "(http://www.|https://www.|http://|https://)?(reddit|old.reddit)" +
                "(.[a-z]+/r/[a-zA-Z]+/comments/[a-z0-9]+/)";
        return RegexMatcher.contains(messageContent, pattern);
    }

    private boolean isYoutubeSetTime(String messageContent) {

        String pattern = "^((?:https?:)?\\/\\/)?((?:www|m)\\.)?((?:youtube\\.com|youtu.be))(\\/(?:[\\w\\-]+\\?v=|" +
                "embed\\/|v\\/)?)([\\w\\-]+)(\\S+)?(\\s[0-9]+){1,3}$";
        return RegexMatcher.matches(messageContent, pattern);
    }
}
