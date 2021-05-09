package bot.command.commands;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;
import bot.command.commands.timer.TimerStartCommand;

/**
 * Generates documentation for all possible commands
 */

public class HelpCommand extends Command {

    public static final String commandID = "!help";

    @Override
    public void execute() {

        StringBuilder docs = new StringBuilder();

        docs.append(new NameCommand().constructDocs().printDocs());
        docs.append(new SquareCommand().constructDocs().printDocs());
        docs.append(new TimerStartCommand().constructDocs().printDocs());
        docs.append(new WeatherCommand().constructDocs().printDocs());
        docs.append(new HelpCommand().constructDocs().printDocs());

        Scheduler.scheduleSendText(docs.toString());
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID)
                .summary("Prints all possible commands");
    }
}
