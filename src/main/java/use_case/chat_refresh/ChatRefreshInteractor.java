package use_case.chat_refresh;

import java.util.ArrayList;
import java.util.List;

import entity.Message;

/**
 * Interactor for the Chat Refresh use case.
 */
public class ChatRefreshInteractor implements ChatRefreshInputBoundary
{
    private final ChatRefreshThreadDataAccessInterface threadDataAccessObject;
    private final ChatRefreshOutputBoundary chatRefreshPresenter;

    public ChatRefreshInteractor(
            ChatRefreshThreadDataAccessInterface threadDataAccessObject,
            ChatRefreshOutputBoundary chatRefreshPresenter)
    {
        this.threadDataAccessObject = threadDataAccessObject;
        this.chatRefreshPresenter = chatRefreshPresenter;
    }

    @Override
    public void execute(ChatRefreshInputData inputData)
    {
        try
        {
            Long threadID = inputData.getThreadID();
            List<Message> messageList = threadDataAccessObject.getMessages(threadID);

            List<String[]> messages = new ArrayList<>();
            for (Message message : messageList)
            {
                String[] lst = {
                        message.getSenderUsername(),
                        message.getContent(),
                        message.getTimestamp(),
                };
                messages.add(lst);
            }

            ChatRefreshOutputData outputData = new ChatRefreshOutputData(messages);
            chatRefreshPresenter.prepareSuccessView(outputData);

        }
        catch (Exception exception)
        {
            chatRefreshPresenter.prepareFailView("Error: " + exception.getMessage());
        }
    }
}
