package bot.commands.timer;

import bot.HelpDocsBuilder;
import bot.Scheduler;
import bot.commands.Command;

public class TimerStopCommand extends Command {

    public TimerStopCommand() {
        super("TIMER_STOP");
    }

    @Override
    public void execute() {

        Command timerCommand = Scheduler.getCommandByID("TIMER");

        if (timerCommand != null) {
            ((TimerStartCommand) timerCommand).getTimer().stopTimer();
            Scheduler.scheduleSendText("Timer stopped");
        } else {
            Scheduler.scheduleSendText("No timer is currently running");
        }
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return null;
    }
}
