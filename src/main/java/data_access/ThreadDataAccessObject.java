package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.Message;
import entity.Thread;
import use_case.add_thread.AddThreadThreadDataAccessInterface;
import use_case.send_message.SendMessageThreadDataAccessInterface;

/**
 * The DAO for thread data.
 */
public class ThreadDataAccessObject implements AddThreadThreadDataAccessInterface,
        SendMessageThreadDataAccessInterface
{
    private final PingBackend backend;

    public ThreadDataAccessObject(PingBackend backend)
    {
        this.backend = backend;
    }

    @Override
    public Thread save(Thread thread)
    {
        // TODO: code this once the server is up, current code is filler to make it compile.
        List<Message> mockList1 = new ArrayList<>();
        List<String> mockList2 = new ArrayList<>();
        return new Thread("", mockList2, mockList1, 1L);
    }
}
