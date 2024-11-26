package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a thread in our program.
 */
public class Thread
{

    private final Long threadID;
    private String name;
    private final List<String> usernameList;
    private final List<Message> messageList;

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
}

