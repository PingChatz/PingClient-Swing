package entity;

/**
 * The representation of a message in our program.
 * ToDo: Check class to abstract later
 */
public class Message
{

    private final Long threadID;
    private final Long senderID;
    private final Object content;

    public Message(Object content, Thread thread, User sender)
    {
        this.content = content;
        this.threadID = thread.getThreadID();
        this.senderID = sender.getUserID();
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
}
