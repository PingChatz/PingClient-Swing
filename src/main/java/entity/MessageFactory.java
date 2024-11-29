package entity;

/**
 * Factory for creating messages.
 */
public class MessageFactory
{
    /**
     * This method creates a new message object, with its timestamp set to null.
     * @param content content of the message
     * @param senderUsername the username of the sender
     * @return a new user object
     */
    public Message create(String content, String senderUsername)
    {
        return new Message(content, senderUsername);
    }
}
