package bot.commands;

import bot.HelpDocsBuilder;
import bot.Interactor;
import bot.Scheduler;

/**
 * !square command
 */
public class SquareCommand extends Command
{

    int num;

    public SquareCommand() {
        super("SQUARE");
    }

    public SquareCommand(String integer)
    {
        super("SQUARE");

        try
        {
            num = Integer.parseInt(integer);
        }
        catch (NumberFormatException e)
        {
            System.out.println("Unreachable statement");
        }
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID + " [Int]")
                .summary("Squares the given number");
    }

    @Override
    public void execute()
    {
        Scheduler.scheduleSendText(num * num + "");
    }
}
