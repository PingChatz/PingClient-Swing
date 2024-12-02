package interface_adapter.chat_refresh;

import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import use_case.chat_refresh.ChatRefreshOutputBoundary;
import use_case.chat_refresh.ChatRefreshOutputData;

/**
 * Controller for the Chat Refresh Use Case.
 */
public class ChatRefreshPresenter implements ChatRefreshOutputBoundary
{
    private final ChatViewModel chatViewModel;

    public ChatRefreshPresenter(ChatViewModel chatViewModel)
    {
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void prepareSuccessView(ChatRefreshOutputData outputData)
    {
        ChatState chatState = chatViewModel.getState();
        chatState.setAllMessages(outputData.getMessages());
        chatViewModel.setState(chatState);
        chatViewModel.firePropertyChanged("full_message_update");
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        ChatState chatState = chatViewModel.getState();
        chatState.setSendMessageError(errorMessage);
        chatViewModel.setState(chatState);
        chatViewModel.firePropertyChanged();
    }
}
