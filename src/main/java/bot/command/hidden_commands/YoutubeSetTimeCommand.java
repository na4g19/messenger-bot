package bot.command.hidden_commands;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;
import bot.command.commands.Command;

/**
 * Sets time mark in youtube video
 * Usage: URL [HOURS_INT](optional) [MINUTES_INT](optional) [SECONDS_INT]
 */
public class YoutubeSetTimeCommand extends Command {

    private String youtubeUrl = "";

    public static final String commandID = "YOUTUBE_SET_TIME_INTERNAL";

    public YoutubeSetTimeCommand(String url) {
        youtubeUrl = url;
    }

    @Override
    public void execute() {

        String url = youtubeUrl.substring(0, youtubeUrl.indexOf(" "));
        youtubeUrl = youtubeUrl.substring(youtubeUrl.indexOf(" ") + 1);
        String[] time = youtubeUrl.split(" ");
        int totalSecs = 0;

        for(int i = 0; i < time.length; i++) {
            totalSecs += parseInt(time[i]) * Math.pow(60, time.length - i - 1);
        }

        Scheduler.scheduleSendText(url + "&t=" + totalSecs + "s");
    }

    private int parseInt(String integer) {

        int returnInt = 0;

        try {
            returnInt = Integer.parseInt(integer);
        } catch (NumberFormatException e) {
            System.out.println("Unparsable integer passed");
        }

        return returnInt;
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return null;
    }
}
