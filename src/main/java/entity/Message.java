package entity;

/**
 * The representation of a message in our program.
 * ToDo: Check class to abstract later
 */
public class Message
{

    private final Long threadID;
    private final Long senderID;
    private final String senderUsername;
    private final Object content;

    public Message(Object content, Long threadID, Long senderID, String senderUsername)
    {
        this.content = content;
        this.threadID = threadID;
        this.senderID = senderID;
        this.senderUsername = senderUsername;
    }

    public final Long getSenderID()
    {
        return senderID;
    }

    public final Long getThreadID()
    {
        return threadID;
    }

    public final Object getContent()
    {
        return content;
    }

    public final String getSenderUsername()
    {
        return senderUsername;
    }
}
