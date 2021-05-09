package bot.command.commands;

import bot.command.HelpDocsBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Extended by every command
 */
public abstract class Command {

    public static final Set<String> supportedCommands;

    static {
        supportedCommands = Stream.of("!name", "!square", "!timer start", "!timer stop", "!help", "!weather", "!money")
                .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Which function in command to execute.
     * e.g. !timer start has two functions : with or without [int]
     * functionMode will determine which will execute.
     * Each command constructor must specify functionMode
     * Interpreter chooses one of the constructors
     * Command.execute() executes appropriate function
     */
    protected int functionMode = 0;

    /**
     * Switch over possible functions to execute
     */
    public abstract void execute();

    /**
     * Description of the command
     * How to call + what it does
     * @return description of the command
     */
    public abstract HelpDocsBuilder constructDocs();
}
