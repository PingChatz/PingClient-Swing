package data_access;

import entity.Message;
import use_case.send_message.SendMessageMessageDataAccessInterface;

/**
 * The DAO for message data.
 */
// TODO: implement this.
public class MessageDataAccessObject implements SendMessageMessageDataAccessInterface
{
    private final PingBackend backend;

    public MessageDataAccessObject(PingBackend backend)
    {
        this.backend = backend;
    }

    @Override
    public Message save(Message message, Long threadID)
    {
        // TODO: code this properly to use the returned values from server calls
        return new Message("", "", "");
    }
}
