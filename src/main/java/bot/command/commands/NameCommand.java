package bot.command.commands;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;

/**
 * !name command
 */
public class NameCommand extends Command {

    private static final String commandID = "!name";

    private String name;

    public NameCommand() {
    }

    public NameCommand(String sender) {
        name = sender;
    }

    @Override
    public void execute() {
        Scheduler.scheduleSendText(name);
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID)
                .summary("Prints the name of the sender");
    }
}
