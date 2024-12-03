package interface_adapter.add_thread;

import interface_adapter.ViewManagerModel;
import interface_adapter.threads.ThreadsViewModel;
import use_case.add_thread.AddThreadOutputBoundary;
import use_case.add_thread.AddThreadOutputData;

/**
 * The Presenter for the Add Thread Use Case.
 */
public class AddThreadPresenter implements AddThreadOutputBoundary
{
    private final AddThreadViewModel addThreadViewModel;
    private final ThreadsViewModel threadsViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddThreadPresenter(ViewManagerModel viewManagerModel,
                              AddThreadViewModel addThreadViewModel,
                              ThreadsViewModel threadsViewModel)
    {
        this.addThreadViewModel = addThreadViewModel;
        this.threadsViewModel = threadsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
    {
        // revert add thread view model to blank state
        final AddThreadState state = addThreadViewModel.getState();
        state.setUsernameList("");
        state.setThreadName("");
        state.setAddThreadSuccess(successMessage);
        state.setAddThreadError(null);
        addThreadViewModel.firePropertyChanged("reset-success");

        // update threads view model
        threadsViewModel.getState().addThread(outputData.getThreadID(), outputData.getThreadName());
        threadsViewModel.firePropertyChanged("update-thread-list");

        // switch back to the threads view
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        final AddThreadState state = addThreadViewModel.getState();
        state.setAddThreadSuccess(null);
        state.setAddThreadError(errorMessage);
        addThreadViewModel.firePropertyChanged();
    }

    @Override
    public void switchToThreadsView()
    {
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
