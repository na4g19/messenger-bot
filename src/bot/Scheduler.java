package bot;

import java.util.HashMap;
import java.util.Map;

import bot.commands.Command;

// FIXME: 15/02/2021 MEGA GIGA SUPER ALFA VERSIJA
// FIXME: 15/02/2021 change static to singleton

/**
 * Tracks commands executing in the background
 */
public class Scheduler {

    // Maps unique IDs to commands executing in the background
    private static Map<String, Command> runningCommands = new HashMap<>();

    // FIXME: 15/02/2021 generate unique ids to allow multiple timers
    public static void addCommand(Command command) {
        runningCommands.put(command.getCommandID(), command);
    }

    /**
     * Checks if a command with specified ID is running in the background
     *
     * @param ID of the command to be checked
     * @return true if command is running, false otherwise
     */
    public static boolean contains(String ID) {
        return runningCommands.containsKey(ID);
    }

    /**
     * Returns the command with the specified id
     *
     * @param id id of the command
     * @return the command
     */
    public static Command getRunningCommand(String id) {
        return runningCommands.get(id);
    }

    /**
     * Removes the command with the specified id
     *
     * @param id id of the command
     */
    public static void removeRunningCommand(String id) {
        runningCommands.remove(id);
    }

    /**
     * Remove commands that finished executing
     */
    public void removeCommands() {

    }
}
