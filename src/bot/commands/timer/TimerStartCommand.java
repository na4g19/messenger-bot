package bot.commands.timer;

import bot.HelpDocsBuilder;
import bot.Scheduler;
import bot.Tokenizer.Token;
import bot.commands.Command;
import bot.exceptions.IllegalCommandException;

import java.util.LinkedList;

public class TimerStartCommand extends Command {

    private Timer timer;
    private LinkedList<Token> tokens;

    public TimerStartCommand() {
        super("TIMER");
    }

    public TimerStartCommand(LinkedList<Token> tokens) {
        super("TIMER");
        this.tokens = tokens;
    }

    @Override
    public void execute() {

        if (tokens.get(1).getSequence().equals("start")) {

            if (tokens.size() == 2) {
                startTimer();
            } else {
                startTimer(tokens.get(2).getSequence());
            }
        }
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID)
                .summary("Invokes timer functions")
                .option("start", "Starts a new timer")
                .option("start [Int]", "Starts a new timer for the given amount of minutes");
    }

    /**
     * Starts timer for unlimited time until it's stopped
     */
    private void startTimer() {

        // Checks if this command is a second timer
        if (Scheduler.isProcessRunning(commandID + "1")) {
            Scheduler.scheduleSendText("Another timer is already running");
            return;
        }

        Scheduler.scheduleSendText("Timer started");
        timer = new Timer();
        timer.start();
    }

    /**
     * Starts timer for specified number of minutes
     *
     * @param time the number of minutes for timer to run
     */
    private void startTimer(String time) {

        // Checks if this command is a second timer
        if (Scheduler.isProcessRunning(commandID + "1")) {
            Scheduler.scheduleSendText("Another timer is already running");
            return;
        }

        int minutes = 0;

        try {
            minutes = Integer.parseInt(time);
        } catch (NumberFormatException e) {
            System.out.println("Unreachable statement");
        }

        if (minutes <= 0) {
            throw new IllegalCommandException("Minutes must be more than zero");
        }

        timer = new Timer(minutes);
        Scheduler.scheduleSendText("Timer started for " + minutes + " minutes");
        timer.start();
    }

    /**
     * Getter for timer
     * @return
     */
    public Timer getTimer() {
        return timer;
    }
}
