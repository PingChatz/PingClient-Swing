package interface_adapter.send_message;

import interface_adapter.ViewManagerModel;
import interface_adapter.threads.ThreadsViewModel;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

/**
 * The Presenter for the Send Message Use Case.
 */
public final class SendMessagePresenter implements SendMessageOutputBoundary
{
    // == INSTANCE VARIABLES ==
    private final ChatViewModel chatViewModel;
    private final ThreadsViewModel threadsViewModel;
    private final ViewManagerModel viewManagerModel;

    // == CONSTRUCTOR ==
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
        // TODO: add the timestamp once more progress has been completed on refresh use case
        chatState.addMessage(outputData.getSenderUsername(), outputData.getContent());
        chatState.setSendMessageError(null);
        chatViewModel.firePropertyChanged("full_message_update");
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
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
