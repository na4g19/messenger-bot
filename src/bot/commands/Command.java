package bot.commands;

import bot.HelpDocsBuilder;
import bot.Interactor;

/**
 * Extended by every command
 */
public abstract class Command {

    // Used to identify the command
    protected final String commandID;

    public Command(String ID)
    {
        commandID = ID;
    }

    /**
     * What the command does
     */
    public abstract void execute(Interactor interactor);

    /**
     * Description of the command
     * How to call + what it does
     * @return description of the command
     */
    public abstract HelpDocsBuilder constructDocs();

    /**
     * Getter for ID
     * @return the ID of the command
     */
    public String getCommandID() {
        return commandID;
    }
}
