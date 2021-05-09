package bot.command.commands.timer;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;
import bot.command.commands.Command;
import bot.exceptions.IllegalCommandException;

public class TimerStartCommand extends Command {

    private Timer timer;

    private int minutes = 0;

    public static final String commandID = "!timer start";

    /**
     * Timer with no time constraint
     */
    public TimerStartCommand() {
    }

    /**
     * Timer set for certain number of minutes
     */
    public TimerStartCommand(int minutes) {
        this.minutes = minutes;
        functionMode = 1;
    }

    @Override
    public void execute() {

        switch (functionMode) {
            case 0:
                startTimer();
                break;
            case 1:
                startTimer(minutes);
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
    private void startTimer(int minutes) {

        // Checks if this command is a second timer
        if (Scheduler.isProcessRunning(commandID + "1")) {
            Scheduler.scheduleSendText("Another timer is already running");
            return;
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
