package interface_adapter.threads;

import java.util.HashMap;
import java.util.Map;

/**
 * This class keeps track of the user's username while they are in a logged in state(in the threads view)
 * so that they can log out.
 */
public class ThreadsState
{

    private String username = "";

    // Not sure if hashmap is the right data type
    private Map<Long, String> threadHash = new HashMap<>();
    private String password = "";
    private String passwordError;

    public ThreadsState(ThreadsState copy)
    {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
        threadHash = copy.threadHash;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ThreadsState()
    {

    }

    public final String getUsername()
    {
        return username;
    }

    public final void setUsername(String username)
    {
        this.username = username;
    }

    public final Map<Long, String> getThreadHash()
    {
        return threadHash;
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
