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
        // TODO: implement this.
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        // TODO: optional: decide whether or not you want to restrict message content in any way.
    }

    @Override
    public void switchToThreadsView()
    {
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
