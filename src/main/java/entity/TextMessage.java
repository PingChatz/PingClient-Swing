package entity;

/**
 * The representation of a text message in our program.
 */
public class TextMessage extends Message
{

    // TextMessage specifies that the type of the content is String
    public TextMessage(Object content, Long threadID, Long senderID, String senderUsername)
    {
        super(content, threadID, senderID, senderUsername);
    }
}
