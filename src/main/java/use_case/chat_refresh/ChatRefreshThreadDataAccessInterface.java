package use_case.chat_refresh;

import java.util.List;

import entity.Message;

/**
 * DAO for the Chat Refresh Use Case ; for the Thread.
 * Contains all necessary methods to create a new thread.
 */
public interface ChatRefreshThreadDataAccessInterface
{

    /**
     * Updates the current message thread when the refresh is triggered.
     * If a new message has been sent, it is added to the thread
     * @param threadID the ID of the current thread
     * @return the list of messages in the thread
     * @throws Exception if the server call fails
     */
    List<Message> getMessages(Long threadID) throws Exception;
}
