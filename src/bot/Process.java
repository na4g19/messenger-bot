package bot;

import bot.commands.Command;

public class Process implements Runnable {

    private Command command;
    private String processID;

    public Process(Command command) {
        this.command = command;
        processID = command.getCommandID();
    }

    @Override
    public void run() {
        command.execute();
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
