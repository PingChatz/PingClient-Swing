package entity;

/**
 * The representation of a text message in our program.
 */
public class TextMessage extends Message
{

    // TextMessage specifies that the type of the content is String
    public TextMessage(String content, Thread thread, User sender)
    {
        super(content, thread, sender);
    }
}
