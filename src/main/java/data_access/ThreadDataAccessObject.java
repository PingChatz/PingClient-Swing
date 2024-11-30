package data_access;

import java.util.List;

import entity.Message;
import entity.Thread;
import org.json.JSONObject;
import use_case.add_thread.AddThreadThreadDataAccessInterface;
import use_case.send_message.SendMessageThreadDataAccessInterface;

/**
 * The DAO for thread data.
 */
public class ThreadDataAccessObject implements SendMessageThreadDataAccessInterface,
        AddThreadThreadDataAccessInterface
{
    private final PingBackend backend;

    public ThreadDataAccessObject(PingBackend backend)
    {
        this.backend = backend;
    }

    @Override
    public String getCurrentThreadName()
    {
        // get stuff from the api
        return "";
    }

    @Override
    public String getCurrentThreadID()
    {
        return "";
    }

    @Override
    public List<Message> getMessageList()
    {
        return List.of();
    }

    @Override
    public void updateMessageList(Message message)
    {

    }

    @Override
    public Thread save(Thread thread) throws Exception
    {
        // tell server to create a new thread
        JSONObject serverOutput = backend.createThread(
                thread.getName(), thread.getUsernameList().toArray(new String[0]));

        // read from the JSONObject and create a thread to return
        // TODO: create this once you have the information on what the outputted JSON looks like
        return null;
    }
}
