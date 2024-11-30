package data_access;

import entity.Message;
import org.json.JSONObject;
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
    public Message save(Message message, Long threadID) throws Exception
    {
        // call the server
        JSONObject serverOutput = backend.sendMessage(
                Integer.parseInt(String.valueOf(threadID)), "text", message.getContent());

        // format the JSON into a new Message object
        // TODO: JSON formatting (+ timestamp formatting)
        return new Message("", "", "");
    }
}
