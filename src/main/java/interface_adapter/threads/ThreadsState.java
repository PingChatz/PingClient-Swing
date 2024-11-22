package interface_adapter.threads;

import java.util.HashMap;

/**
 * This class keeps track of the user's username while they are in a logged in state(in the threads view)
 * so that they can log out.
 */
public class ThreadsState
{

    private String username = "";

    // Not sure if hashmap is the right data type
    private HashMap<Long, String> threadHash = new HashMap<>();
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

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public HashMap<Long, String> getThreadHash()
    {
        return threadHash;
    }

    public void addThread(Long threadId, String threadName)
    {
        this.threadHash.put(threadId, threadName);
    }
}
