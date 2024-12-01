package use_case.send_message;

import entity.Message;

/**
 * DAO for the Send Message Use Case ; for the Message.
 */
public interface SendMessageMessageDataAccessInterface
{
    /**
     * Saves the sent message and returns a message object with a timestamp from the server.
     *
     * @param message  the sent message to save
     * @param threadID the ID of the thread to save the message within
     * @return a representation of the new message that was created
     */
    Message save(Message message, Long threadID) throws Exception;
}
