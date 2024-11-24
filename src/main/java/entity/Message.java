package entity;

/**
 * The representation of a message in our program.
 */
public class Message
{

    private final Long threadID;
    private final Long senderID;
    private final String senderUsername;
    private final String content;

    public Message(String content, Long threadID, Long senderID, String senderUsername)
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

    public final String getContent()
    {
        return content;
    }

    public final String getSenderUsername()
    {
        return senderUsername;
    }
}
