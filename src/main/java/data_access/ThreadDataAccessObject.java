package data_access;

import entity.Message;
import entity.Thread;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.add_thread.AddThreadThreadDataAccessInterface;
import use_case.get_threads.GetThreadsThreadDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The DAO for thread data.
 */
public class ThreadDataAccessObject implements AddThreadThreadDataAccessInterface, GetThreadsThreadDataAccessInterface
{
    private static final String PARTICIPANTS = "participants";
    private final PingBackend backend;

    public ThreadDataAccessObject(PingBackend backend)
    {
        this.backend = backend;
    }

    // very similar logic throughout these three methods
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
                Long threadID = threadJson.getLong("threadId");

                if (threadIDs.contains(threadID))
                {
                    String name = threadJson.getString("threadName");
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
                JSONArray participantsArray = threadJson.getJSONArray("participants");

                if (participantsArray.toList().contains(username))
                {
                    Long threadID = threadJson.getLong("threadId");
                    String name = threadJson.getString("threadName");

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
    public Thread save(Thread thread) throws Exception
    {
        // Send a request to the server to create a new thread
        JSONObject serverOutput = backend.createThread(
                thread.getName(), thread.getUsernameList().toArray(new String[0]));

        // if it's the error JSON object:
        if (serverOutput.has("error"))
        {
            // Throw an exception with the server error message
            String errorMessage = serverOutput.getString("message");
            throw new IOException("Server Error: " + errorMessage);
        }

        // If the outputted usernameList contains the same username twice
        if (hasDuplicateUsernames(serverOutput))
        {
            throw new IllegalArgumentException(
                    "Duplicate or self-included usernames found in the participant list.");
        }

        // Parse the successful response and create a new Thread object
        if (serverOutput.has("threadId") && serverOutput.has("threadName") && serverOutput.has(
                PARTICIPANTS))
        {
            Long threadID = serverOutput.getLong("threadId");
            String threadName = serverOutput.getString("threadName");
            List<String> usernameList = new ArrayList<>();
            JSONArray participantArray = serverOutput.getJSONArray(PARTICIPANTS);
            for (int i = 0; i < participantArray.length(); i++)
            {
                usernameList.add(participantArray.getString(i));
            }
            List<Message> emptyMessageList = new ArrayList<>();

            // Return the new Thread object with an empty messageList
            return new Thread(threadName, usernameList, emptyMessageList, threadID);
        }

        // If the response doesn't match either expected format, throw an exception
        throw new IOException("Unexpected server response: " + serverOutput);
    }

    private boolean hasDuplicateUsernames(JSONObject serverOutput)
    {
        if (serverOutput.has(PARTICIPANTS))
        {
            JSONArray participants = serverOutput.getJSONArray(PARTICIPANTS);
            Set<String> uniqueUsernames = new HashSet<>();
            for (int i = 0; i < participants.length(); i++)
            {
                String username = participants.getString(i);
                if (!uniqueUsernames.add(username))
                {
                    // If add() returns false, a duplicate username was found
                    return true;
                }
            }
        }
        return false;
    }
}
