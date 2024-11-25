package interface_adapter.add_thread;

import use_case.add_thread.AddThreadInputBoundary;
import use_case.add_thread.AddThreadInputData;

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
     * @param usernameList is a comma separated string of usernames
     * @param threadName the name of the new thread.
     */
    public void execute(String threadName, String usernameList)
    {
        AddThreadInputData inputData = new AddThreadInputData(threadName, usernameList);
        addThreadUseCaseInteractor.execute(inputData);
    }

    /**
     * Executes the "switch to ThreadsView" Use Case.
     */
    public void switchToThreadsView()
    {
        addThreadUseCaseInteractor.switchToThreadsView();
    }
}
