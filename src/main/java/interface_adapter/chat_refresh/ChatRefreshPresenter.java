package interface_adapter.chat_refresh;

import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import use_case.chat_refresh.ChatRefreshOutputData;

public class ChatRefreshPresenter
{
    private final ChatViewModel chatViewModel;


    public ChatRefreshPresenter(ChatViewModel chatViewModel)
    {
        this.chatViewModel = chatViewModel;
    }

    public void prepareSucessView(ChatRefreshOutputData chatRefreshOutputData)
    {
        final ChatState chatState = chatViewModel.getState();

        //TODO Change setAllMessages method input data (look at commented below)

        //chatState.setAllMessages(chatRefreshOutputData.get_messages());

        chatViewModel.firePropertyChanged();
    }
}