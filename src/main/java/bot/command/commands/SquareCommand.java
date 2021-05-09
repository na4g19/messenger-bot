package bot.command.commands;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;

/**
 * !square command
 */
public class SquareCommand extends Command {

    private static final String commandID = "!square";

    private int number;

    public SquareCommand() {
    }

    public SquareCommand(int number) {
        this.number = number;
    }

    @Override
    public void execute() {
        Scheduler.scheduleSendText(number * number + "");
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID + " [Int]")
                .summary("Squares the given number");
    }
}
