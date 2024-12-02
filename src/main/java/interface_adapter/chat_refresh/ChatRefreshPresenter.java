package interface_adapter.chat_refresh;

import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import use_case.chat_refresh.ChatRefreshOutputBoundary;
import use_case.chat_refresh.ChatRefreshOutputData;

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
        chatViewModel.firePropertyChanged("full_message_update");
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        // Handle failure (e.g., show an error message)
    }
}
