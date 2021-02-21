package bot.commands;

import bot.HelpDocsBuilder;
import bot.Scheduler;
import bot.Interactor;
import bot.Tokenizer.Token;
import bot.exceptions.IllegalCommandException;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class TimerCommand extends Command implements BackgroundRunnable {

    private Interactor interactor;
    private Timer timer;
    private LinkedList<Token> tokens;

    public TimerCommand() {
        super("TIMER");
    }

    public TimerCommand(LinkedList<Token> tokens) {
        super("TIMER");
        this.tokens = tokens;
    }

    @Override
    public void execute(Interactor interactor) {

        this.interactor = interactor;

        if(tokens.get(1).getSequence().equals("start")) {

            if(tokens.size() == 2) {
                startTimer();
            } else {
                startTimer(tokens.get(2).getSequence());
            }
        } else {
            stopTimer();
        }
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return new HelpDocsBuilder()
                .name(commandID)
                .summary("Invokes timer functions")
                .option("start", "Starts a new timer")
                .option("start [Int]", "Starts a new timer for the given amount of minutes")
                .option("stop", "Stops the timer");
    }

    /**
     * Starts timer for unlimited time until it's stopped
     */
    private void startTimer() {

        // Check if another timer is running
        if (Scheduler.contains(commandID)) {
            throw new IllegalCommandException("Another timer is already running");
        }

        timer = new Timer();
        startTimerThread();
        interactor.sendText("Timer started");
    }

    /**
     * Starts timer for specified number of minutes
     * @param time the number of minutes for timer to run
     */
    private void startTimer(String time) {

        // Check if another timer is running
        if (Scheduler.contains(commandID)) {
            throw new IllegalCommandException("Another timer is already running");
        }

        int minutes = 0;

        try {
            minutes = Integer.parseInt(time);
        } catch (NumberFormatException e) {
            System.out.println("Unreachable statement");
        }

        if(minutes <= 0) {
            throw new IllegalCommandException("Minutes must be more than zero");
        }

        timer = new Timer(minutes);
        startTimerThread();
        interactor.sendText("Timer started for " + minutes + " minutes");
    }

    /**
     * Stops a running timer
     */
    private void stopTimer() {

        Command timerCommand = Scheduler.getRunningCommand(commandID);

        if (timerCommand != null) {

            ((TimerCommand) timerCommand).timer.stop = true;
            Scheduler.removeRunningCommand(commandID);
            interactor.sendText("Timer stopped");
        } else {
            interactor.sendText("No timer is currently running");
        }
    }

    /**
     * Starts timer in new thread
     */
    private void startTimerThread() {
        Scheduler.addCommand(this);
        new Thread( ( (TimerCommand) Scheduler.getRunningCommand(commandID) ).timer).start();
    }

    // FIXME: 10/02/2021 up to 24h
    private class Timer implements Runnable {

        private final long start;
        private final long finish;

        private boolean stop = false;

        public Timer() {
            start = System.currentTimeMillis();
            finish = Long.MAX_VALUE;
        }

        public Timer(int minutes) {
            start = System.currentTimeMillis();
            finish = TimeUnit.MINUTES.toMillis(minutes) + start;
        }

        @Override
        public void run() {

            // Waits
            while (!isTimeUp() && !stop) {

                // Limit check period
                try {
                    Thread.sleep(10);
                } catch (InterruptedException exception) {
                    System.err.println("Timer thread got interrupted");
                }
            }

            printResult();
        }

        private boolean isTimeUp() {
            return finish <= System.currentTimeMillis();
        }

        private void printResult() {

            if (stop) {

                long currentMillis = System.currentTimeMillis() - start;

                long hours = TimeUnit.MILLISECONDS.toHours(currentMillis);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(currentMillis) - hours * 60;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(currentMillis) - minutes * 60 - hours * 3600;

                interactor.sendText(hours + " hours " + minutes + " minutes " + seconds + " seconds");

            } else {
                interactor.sendText("Time is up");
            }
        }
    }
}
