package interface_adapter.threads;

import java.util.HashMap;
import java.util.Map;

/**
 * This class keeps track of the user's username while they are in a logged in state(in the threads view)
 * so that they can log out.
 */
public class ThreadsState
{

    private String currentUsername = "";
    private Map<Long, String> threadHash = new HashMap<>();

    public ThreadsState(ThreadsState copy)
    {
        currentUsername = copy.currentUsername;
        threadHash = copy.threadHash;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ThreadsState()
    {
    }

    public final String getCurrentUsername()
    {
        return currentUsername;
    }

    public final void setCurrentUsername(String currentUsername)
    {
        this.currentUsername = currentUsername;
    }

    public final Map<Long, String> getThreadHash()
    {
        return threadHash;
    }

    public final void setThreadHash(Map<Long, String> threadHash)
    {
        this.threadHash = threadHash;
    }

    /**
     * Returns an array of thread names from threadHash.
     * @return an array of thread names
     */
    public final String[] getThreadNamesList()
    {
        return threadHash.values().toArray(new String[0]);
    }

    /**
     * Add a thread to the list of threads in this View State.
     * @param threadId the ID of the added thread
     * @param threadName the name of the added thread
     */
    public final void addThread(Long threadId, String threadName)
    {
        this.threadHash.put(threadId, threadName);
    }
}
