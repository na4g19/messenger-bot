package bot;

import bot.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Schedules the execution of commands
 */
public class Scheduler implements Runnable {

    // Interactor shared between all processes
    private static Interactor interactor;

    // Maps unique IDs to threads executing in the background
    private static Map<String, Thread> runningThreads = new ConcurrentHashMap<>();

    // Maps unique IDs to processes executing in the background
    private static Map<String, Process> runningProcesses = new ConcurrentHashMap<>();

    /**
     * Starts the process in a new thread
     * @param process
     */
    public static void scheduleProcess(Process process) {
        Thread thread = new Thread(process);
        String id = Scheduler.assignUniqueID(process);
        runningThreads.put(id, thread);
        runningProcesses.put(id, process);
        runningThreads.get(id).start();
    }

    /**
     * Thread waits for it's turn to send text
     * @param text
     */
    public synchronized static void scheduleSendText(String text) {
        interactor.sendText(text);
    }

    /**
     * Thread waits for it's turn to send media
     * @param fileName
     */
    public synchronized static void scheduleSendMedia(String fileName) {
        interactor.sendMedia(fileName);
    }

    /**
     * Assigns an interactor to the scheduler
     * @param localInteractor
     */
    public static void startScheduler(Interactor localInteractor) {

        interactor = localInteractor;
        Thread thread = new Thread(new Scheduler());
        runningThreads.put("SCHEDULER", thread);
        thread.start();
    }

    /**
     * Assigns a unique ID to each process
     * @param process
     * @return
     */
    private static String assignUniqueID(Process process) {
        
        String id = process.getProcessID();
        int i = 1;
        
        while(runningThreads.containsKey(id)) {
            id = process.getProcessID() + i;
            i++;
        }
        return id;
    }

    /**
     * Checks if process with spefcified id is running
     * @param id
     * @return true if running, false otherwise
     */
    public static boolean isProcessRunning(String id) {
        return runningThreads.containsKey(id);
    }

    /**
     * Returns a running command with the given ID, or null if command isn't executing
     * @param id
     * @return
     */
    public static Command getCommandByID(String id) {
        return runningProcesses.containsKey(id) ? runningProcesses.get(id).getCommand() : null;
    }

    /**
     * Scheduler runs in a new thread, constantly removing any processes that have finished executing
     */
    @Override
    public void run() {
        
        while(true) {

            // FIXME: 26/03/2021 kas kazkiek laiko
            // FIXME: 26/03/2021 race condition when checking from other threads
            for(Map.Entry<String, Thread> command : runningThreads.entrySet()) {

                if(command.getValue().getState().equals(Thread.State.TERMINATED)) {
                    runningThreads.remove(command.getKey());
                    runningProcesses.remove(command.getKey());
                }
            }
        }
    }
}
