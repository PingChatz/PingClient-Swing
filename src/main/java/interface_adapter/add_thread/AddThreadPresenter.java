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
    public void prepareSuccessView(AddThreadOutputData outputData)
    {
        // TODO: output a little java guy saying: "[threadName] successfully created",
        //  then return to ThreadsView automatically
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        // TODO: implement exactly as the rest of the use cases
    }

    @Override
    public void switchToThreadsView()
    {
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
