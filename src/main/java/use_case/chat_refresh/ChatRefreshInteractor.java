package use_case.chat_refresh;

import entity.Message;

import java.util.ArrayList;
import java.util.List;

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
            List<Message> messageList = threadDataAccessObject.getMessages(inputData.getThreadID());

            List<String[]> messages = new ArrayList<>();
            for (Message message : messageList)
            {
                String[] lst = {
                        message.getSenderUsername(),
                        message.getContent(),
                        message.getTimestamp()
                };
                messages.add(lst);
            }

            ChatRefreshOutputData outputData = new ChatRefreshOutputData(messages);
            chatRefreshPresenter.prepareSuccessView(outputData);

        } catch (Exception e)
        {
            chatRefreshPresenter.prepareFailView("Error: " + e.getMessage());
        }
    }
}
