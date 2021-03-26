package bot.commands;

import bot.HelpDocsBuilder;
import bot.Interactor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Extended by every command
 */
public abstract class Command {

    // Used to identify the command
    protected final String commandID;

    // Commands that can be called directly
    public static final Set<String> callableCommands = Stream.of("!name", "!square", "!timer", "!help", "!weather")
            .collect(Collectors.toCollection(HashSet::new));

    public Command(String ID)
    {
        commandID = ID;
    }

    /**
     * What the command does
     */
    public abstract void execute();

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
