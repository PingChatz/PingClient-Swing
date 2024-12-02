package interface_adapter.chat_refresh;

import use_case.chat_refresh.ChatRefreshInputBoundary;
import use_case.chat_refresh.ChatRefreshInputData;

/**
 * Controller for the Chat Refresh Use Case.
 */
public class ChatRefreshController
{
    private final ChatRefreshInputBoundary chatRefreshInteractor;

    public ChatRefreshController(ChatRefreshInputBoundary chatRefreshInteractor)
    {
        this.chatRefreshInteractor = chatRefreshInteractor;
    }

    /**
     * Executes the Chat Refresh use case.
     * @param threadID the ID of the currently open thread
     */
    public void execute(Long threadID)
    {
        ChatRefreshInputData inputData = new ChatRefreshInputData(threadID);
        chatRefreshInteractor.execute(inputData);
    }
}
