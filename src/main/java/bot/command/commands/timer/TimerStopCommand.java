package bot.command.commands.timer;

import bot.Scheduler;
import bot.command.HelpDocsBuilder;
import bot.command.commands.Command;

public class TimerStopCommand extends Command {

    public static final String commandID = "!timer stop";

    public TimerStopCommand() {

    }

    public void executeTimerStop() {

        Command timerCommand = Scheduler.getCommandByID(TimerStartCommand.commandID);

        if (timerCommand != null) {
            ((TimerStartCommand) timerCommand).getTimer().stopTimer();
            Scheduler.scheduleSendText("Timer stopped");
        } else {
            Scheduler.scheduleSendText("No timer is currently running");
        }
    }

    @Override
    public void execute() {
        executeTimerStop();
    }

    @Override
    public HelpDocsBuilder constructDocs() {
        return null;
    }
}