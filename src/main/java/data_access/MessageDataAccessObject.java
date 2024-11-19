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
    public void save(Message message)
    {
    }
}
