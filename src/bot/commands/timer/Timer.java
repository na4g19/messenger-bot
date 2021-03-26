package bot.commands.timer;

import bot.Scheduler;

import java.util.concurrent.TimeUnit;

public class Timer {

    private final long start;
    private final long finish;

    // Flag indicating if the timer should continue to run
    private boolean stop = false;

    /**
     * Timer until stop command
     */
    public Timer() {
        start = System.currentTimeMillis();
        finish = Long.MAX_VALUE;
    }

    /**
     * Timer for X minutes or until stop command
     * @param minutes
     */
    public Timer(int minutes) {
        start = System.currentTimeMillis();
        finish = TimeUnit.MINUTES.toMillis(minutes) + start;
    }

    /**
     * Starts timer
     */
    public void start() {

        // Waits
        while (!isTimeUp() && !stop) {

             //Limit check period
            try {
                Thread.sleep(10);
            } catch (InterruptedException exception) {
                System.err.println("Timer thread got interrupted");
            }
        }

        printResult();
    }

    /**
     * Checks if timer has finished
     * @return
     */
    private boolean isTimeUp() {
        return finish <= System.currentTimeMillis();
    }

    /**
     * Prints either the total time elapsed or the message indicating the end of timer period
     */
    private void printResult() {

        if (stop) {

            long currentMillis = System.currentTimeMillis() - start;

            long hours = TimeUnit.MILLISECONDS.toHours(currentMillis);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(currentMillis) - hours * 60;
            long seconds = TimeUnit.MILLISECONDS.toSeconds(currentMillis) - minutes * 60 - hours * 3600;

            Scheduler.scheduleSendText(hours + " hours " + minutes + " minutes " + seconds + " seconds");

        } else {
            Scheduler.scheduleSendText("Time is up");
        }
    }

    public void stopTimer() {
        stop = true;
    }
}
