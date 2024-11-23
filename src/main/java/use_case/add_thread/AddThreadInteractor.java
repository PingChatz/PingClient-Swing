package use_case.add_thread;

/**
 * The Add Thread Interactor.
 */
public class AddThreadInteractor implements AddThreadInputBoundary
{
    // TODO: create the 2 necessary DAIs
    // TODO: import the DAO for threads and users (message DAO not needed here)
    // TODO: create and import a ThreadFactory

    private final AddThreadOutputBoundary addThreadPresenter;

    public AddThreadInteractor(AddThreadOutputBoundary addThreadPresenter)
    {
        this.addThreadPresenter = addThreadPresenter;
    }

    @Override
    public void execute(AddThreadInputData addThreadInputData)
    {
        // TODO: the entire use case logic goes here
    }

    @Override
    public void switchToThreadsView()
    {
        addThreadPresenter.switchToThreadsView();
    }
}
