package data_access;

import java.util.List;

import entity.Message;
import use_case.send_message.SendMessageThreadDataAccessInterface;

/**
 * The DAO for thread data.
 */
// TODO: implement this.
public class ThreadDataAccessObject implements SendMessageThreadDataAccessInterface
{
    @Override
    public String getCurrentThreadName()
    {
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
}
