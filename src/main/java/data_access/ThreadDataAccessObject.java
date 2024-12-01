package data_access;

import entity.Message;
import entity.Thread;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.add_thread.AddThreadThreadDataAccessInterface;
import use_case.get_threads.GetThreadsThreadDataAccessInterface;
import use_case.send_message.SendMessageThreadDataAccessInterface;

import java.util.ArrayList;
import java.util.List;


/**
 * The DAO for thread data.
 */
// TODO: implement this.
public class ThreadDataAccessObject implements SendMessageThreadDataAccessInterface,
        AddThreadThreadDataAccessInterface, GetThreadsThreadDataAccessInterface
{
    private final PingBackend backend;

    public ThreadDataAccessObject(PingBackend backend)
    {
        this.backend = backend;
    }

    //very similar logic throughout these three methods
    @Override
    public List<Long> getUserThreadIDs(Long userID)
    {
        try
        {
            JSONObject response = backend.getThreads();
            JSONArray threadsArray = response.getJSONArray("threads");

            List<Long> threadIDs = new ArrayList<>();
            for (int i = 0; i < threadsArray.length(); i++)
            {
                JSONObject threadJson = threadsArray.getJSONObject(i);
                threadIDs.add(threadJson.getLong("id"));
            }
            return threadIDs;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch thread IDs", e);
        }
    }

    @Override
    public List<Thread> getThreads(List<Long> threadIDs)
    {
        try
        {
            JSONObject response = backend.getThreads();
            JSONArray threadsArray = response.getJSONArray("threads");

            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < threadsArray.length(); i++)
            {
                JSONObject threadJson = threadsArray.getJSONObject(i);
                Long threadID = threadJson.getLong("id");

                if (threadIDs.contains(threadID))
                {
                    String name = threadJson.getString("name");
                    Thread thread = new Thread(threadID, name);
                    threads.add(thread);
                }
            }
            return threads;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch threads", e);
        }
    }

    @Override
    public List<Thread> getThreadsByUsername(String username)
    {
        try
        {
            JSONObject response = backend.getThreads();
            JSONArray threadsArray = response.getJSONArray("threads");

            List<Thread> userThreads = new ArrayList<>();

            for (int i = 0; i < threadsArray.length(); i++)
            {
                JSONObject threadJson = threadsArray.getJSONObject(i);
                JSONArray usernamesArray = threadJson.getJSONArray("usernames");

                if (usernamesArray.toList().contains(username))
                {
                    Long threadID = threadJson.getLong("id");
                    String name = threadJson.getString("name");

                    Thread thread = new Thread(threadID, name);
                    userThreads.add(thread);
                }
            }

            return userThreads;
        } catch (Exception e)
        {
            throw new RuntimeException("Failed to fetch threads for username: " + username, e);
        }
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
