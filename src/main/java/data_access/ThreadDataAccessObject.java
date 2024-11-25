package data_access;

import java.util.List;

import entity.Message;
import entity.Thread;
import use_case.add_thread.AddThreadThreadDataAccessInterface;
import use_case.send_message.SendMessageThreadDataAccessInterface;

/**
 * The DAO for thread data.
 */
// TODO: implement this.
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
    public boolean existsByName(String threadName)
    {
        return false;
    }

    @Override
    public void save(Thread thread)
    {
    }
}
