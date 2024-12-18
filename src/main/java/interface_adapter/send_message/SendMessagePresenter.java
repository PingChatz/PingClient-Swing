package interface_adapter.send_message;

import java.util.ArrayList;

import interface_adapter.ViewManagerModel;
import interface_adapter.threads.ThreadsViewModel;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

/**
 * The Presenter for the Send Message Use Case.
 */
public final class SendMessagePresenter implements SendMessageOutputBoundary
{
    private final ChatViewModel chatViewModel;
    private final ThreadsViewModel threadsViewModel;
    private final ViewManagerModel viewManagerModel;

    public SendMessagePresenter(ViewManagerModel viewManagerModel,
                                ChatViewModel chatViewModel,
                                ThreadsViewModel threadsViewModel)
    {
        this.chatViewModel = chatViewModel;
        this.threadsViewModel = threadsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SendMessageOutputData outputData)
    {
        // Update the chatViewModel with the new message.
        final ChatState chatState = chatViewModel.getState();
        chatState.setMessageInput("");
        chatState.addMessage(outputData.getSenderUsername(), outputData.getContent(), outputData.getTimestamp());
        chatState.setSendMessageError(null);
        chatViewModel.firePropertyChanged("message_update");
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        final ChatState chatState = chatViewModel.getState();
        chatState.setSendMessageError(errorMessage);
        chatState.setMessageInput("");
        chatViewModel.firePropertyChanged();
    }

    @Override
    public void switchToThreadsView()
    {
        // set chat state to default (same current user, however)
        final ChatState chatState = chatViewModel.getState();
        chatState.setSendMessageError(null);
        chatState.setCurrentThreadID(null);
        chatState.setAllMessages(new ArrayList<>());
        chatViewModel.firePropertyChanged("full_message_update");

        // switch the active state in the view manager
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
