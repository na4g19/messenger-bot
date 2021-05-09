package bot;

import bot.antlr.CommandParser;
import bot.command.HiddenCommandDetector;
import bot.command.commands.Command;

public class Process implements Runnable {

    private CommandParser parser;
    private Message message;

    private String processID;
    private Command command = null;

    // If a hidden command was parsed
    private boolean isHidden = false;

    /**
     * Create process for parsing implicit (hidden) commands
     */
    public Process(Message message) {
        this.message = message;
        isHidden = true;
        processID = Scheduler.assignUniqueID("_hidden");
    }

    /**
     * Create process for parsing explicit (starting with !) commands
     */
    public Process(CommandParser parser, Message message) {
        this.parser = parser;
        this.message = message;
        System.out.println(message.getCommandName());
        processID = determineID(message.getCommand());
    }

    /**
     * Scheduler needs to know command ID before command is parsed.
     * Determines command id by checking the beginning of message
     */
    private String determineID(String commandText) {

        for (String id : Command.supportedCommands) {
            if (commandText.startsWith(id)) {
                return id;
            }
        }
        return "undefined";
    }

    @Override
    public void run() {

        if (!isHidden) {
            try {
                command = parser.parse(message);
            } catch (NullPointerException exception) {
                exception.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();

                // FIXME: 05/05/2021 add error messages
                // Scheduler.scheduleSendText(exception.getMessage());
            }
        } else {
            HiddenCommandDetector commandDetector = new HiddenCommandDetector(message);
            if (commandDetector.isExecutable()) {
                command = commandDetector.getCommand();
            }
        }

        if (command != null) {
            command.execute();
        }
    }

    public Command getCommand() {
        return command;
    }

    public String getProcessID() {
        return processID;
    }

    public void setProcessID(String processID) {
        this.processID = processID;
    }
}
