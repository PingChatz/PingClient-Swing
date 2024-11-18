package use_case.send_message;

import entity.Message;

import java.util.List;

/**
 * DAO for the Send Message Use Case ; for the Message.
 * Contains all necessary methods to gather / change the data from / for the message that's being sent.
 */
// TODO: figure out what data we need for the entire send message use case from the Thread.
public interface SendMessageMessageDataAccessInterface
{
    /**
     * Saves the sent message.
     * @param message the sent message to save
     */
    void save(Message message);
}
