package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a thread in our program.
 */
public class Thread
{
    public static final int THREAD_NAME_MAX_LENGTH = 100;
    public static final int THREAD_NAME_MIN_LENGTH = 3;

    private final Long threadID;
    private final List<String> usernameList;
    private final List<Message> messageList;
    private String name;

    // Constructor for the creation of a new Thread with no messages.
    public Thread(String name, List<String> usernameList)
    {
        this.name = name;
        this.usernameList = usernameList;
        this.messageList = new ArrayList<>();
        this.threadID = null;
    }

    // Constructor for a Thread that already exists in the database
    public Thread(String name, List<String> usernameList, List<Message> messageList, Long threadID)
    {
        this.name = name;
        this.usernameList = usernameList;
        this.messageList = messageList;
        this.threadID = threadID;
    }

    // New Constructor for threads fetched from the backend.
    public Thread(Long threadID, String name)
    {
        this.threadID = threadID;
        this.name = name;
        this.usernameList = new ArrayList<>();
        this.messageList = new ArrayList<>();
    }

    public final String getName()
    {
        return this.name;
    }

    public final void setName(String newName)
    {
        this.name = newName;
    }

    public final List<String> getUsernameList()
    {
        return this.usernameList;
    }

    public final Long getThreadID()
    {
        return this.threadID;
    }

    public final List<Message> getMessageList()
    {
        return messageList;
    }

    @Override
    public String toString()
    {
        return "Thread{" + "threadID=" + threadID + ", name='" + name + '\'' + ", usernameList=" + usernameList
                + ", messageList=" + messageList + '}';
    }
}
