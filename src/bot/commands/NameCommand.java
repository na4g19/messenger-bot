package bot.commands;

import bot.HelpDocsBuilder;
import bot.Interactor;
import bot.Scheduler;

/**
 * !name command
 */
public class NameCommand extends Command {

    private String name;

    public NameCommand() {
        super("NAME");
    }

    public NameCommand(String sender) {
        super("NAME");
        name = sender;
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID)
                .summary("Prints the name of the sender");
    }

    @Override
    public void execute() {
        Scheduler.scheduleSendText(name);
    }
}
