package entity;

/**
 * The representation of a message in our program.
 */
public class Message
{
    public static final int MESSAGE_MAX_LENGTH = 280;

    private final String senderUsername;
    private final String content;
    private final String timestamp;

    public Message(String content, String senderUsername)
    {
        this.content = content;
        this.senderUsername = senderUsername;
        this.timestamp = null;
    }

    public Message(String content, String senderUsername, String timestamp)
    {
        this.content = content;
        this.senderUsername = senderUsername;
        this.timestamp = timestamp;
    }

    public final String getContent()
    {
        return content;
    }

    public final String getSenderUsername()
    {
        return senderUsername;
    }

    public final String getTimestamp()
    {
        return timestamp;
    }
}
