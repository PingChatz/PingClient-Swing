package interface_adapter.chat_refresh;

import use_case.chat_refresh.ChatRefreshInputBoundary;
import use_case.chat_refresh.ChatRefreshInputData;

public class ChatRefreshController
{
    private final ChatRefreshInputBoundary chatRefreshInteractor;

    public ChatRefreshController(ChatRefreshInputBoundary chatRefreshInteractor)
    {
        this.chatRefreshInteractor = chatRefreshInteractor;
    }

    public void execute(Long threadID)
    {
        ChatRefreshInputData inputData = new ChatRefreshInputData(threadID);
        chatRefreshInteractor.execute(inputData);
    }
}/**/