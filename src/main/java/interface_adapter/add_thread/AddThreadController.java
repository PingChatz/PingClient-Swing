package interface_adapter.add_thread;

import use_case.add_thread.AddThreadInputBoundary;

/**
 * Controller for the Add Thread Use Case.
 */
public class AddThreadController
{
    private final AddThreadInputBoundary addThreadUseCaseInteractor;

    public AddThreadController(AddThreadInputBoundary addThreadUseCaseInteractor)
    {
        this.addThreadUseCaseInteractor = addThreadUseCaseInteractor;
    }

    /**
     * Executes the Add Thread use case.
     */
    public void execute()
    {
        // TODO: create input data object and probe execute() in use case interactor
    }

    /**
     * Executes the "switch to ThreadsView" Use Case.
     */
    public void switchToThreadsView()
    {
        addThreadUseCaseInteractor.switchToThreadsView();
    }
}
