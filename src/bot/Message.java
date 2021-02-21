package bot;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Represents a message read from the chat
 */
public class Message
{

    private final String sender;
    private final String commandName;
    private final String command;

    private final Time time;

    public Message(String message)
    {

        String[] parts = message.split("[\\s\\n\\r]");

        sender = parts[0];
        time = parseTime(parts[3]);
        commandName = parts[4];

        String[] args = Arrays.copyOfRange(parts, 5, parts.length);

        // Builds command into string for parsing
        StringBuilder commandBuilder = new StringBuilder();
        commandBuilder.append(commandName);

        for (String arg : args)
        {
            commandBuilder.append(" ").append(arg);
        }

        command = commandBuilder.toString();
    }

    /**
     * Converts a String to Time in (HH:mm) format
     *
     * @param time String representation of time
     * @return the hours and minutes the given String represents
     */
    private Time parseTime(String time)
    {

        DateFormat formatter = new SimpleDateFormat("HH:mm");

        try
        {
            return new java.sql.Time(formatter.parse(time).getTime());
        }
        catch (ParseException exception)
        {
            System.err.println("Incorrect time format detected");
            return null;
        }
    }

    public String getCommandName()
    {
        return commandName;
    }

    public String getCommand()
    {
        return command;
    }

    public String getSender()
    {
        return sender;
    }

    public Time getTime()
    {
        return time;
    }
}
