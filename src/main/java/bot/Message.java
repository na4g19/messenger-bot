package bot;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Represents a message read from the chat
 */
public class Message {

    private String sender = null;
    private String commandName = null;
    private String command = null;

    private Time time = null;

    public Message(String message) {
        splitMessage(message);
    }

    /**
     * Splits message into sender + time + command
     */
    private void splitMessage(String message) {

        // Command must consist of atleast two lines
        if (message.split("\n").length < 2) {
            return;
        }

        // Word that marks the end of the user's name
        final String nameEnd = "sent";

        // First line contains the name until the last 'nameEnd'
        String firstLine = message.substring(0, message.indexOf('\n'));

        // Command starts at second line
        command = message.substring(message.indexOf('\n') + 1);
        int nameEnds = firstLine.lastIndexOf(nameEnd);

        // -1 to ignore whitespace
        sender = firstLine.substring(0, nameEnds - 1);

        // Ignore time for now
        time = null; //time = parseTime(firstLine.substring(nameEnds));

        int commandEnds = command.indexOf(' ');
        commandName = commandEnds == -1 ? command : command.substring(0, commandEnds);
    }

    /**
     * Converts a String to Time in (HH:mm) format
     *
     * @param time String representation of time
     * @return the hours and minutes the given String represents
     */
    private Time parseTime(String time) {

        DateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            return new Time(formatter.parse(time).getTime());
        } catch (ParseException exception) {
            System.err.println("Incorrect time format detected");
            return null;
        }
    }

    public String getCommandName() {
        return commandName;
    }

    public String getCommand() {
        return command;
    }

    public String getSender() {
        return sender;
    }

    public Time getTime() {
        return time;
    }
}
