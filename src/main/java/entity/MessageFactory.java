package entity;

/**
 * Factory for creating messages.
 */
public class MessageFactory
{
    /**
     * This method creates a new message object.
     * @param content content of the message
     * @param threadID ID of thread the message was sent in
     * @param senderID ID of the user sending the message
     * @param senderUsername the username of the sender
     * @return a new user object
     */
    public Message create(String content, Long threadID, Long senderID, String senderUsername)
    {
        return new Message(content, threadID, senderID, senderUsername);
    }
}
