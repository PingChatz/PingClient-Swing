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
     *
     * @param threadName      the name of the new thread.
     * @param usernameList    is a comma separated string of usernames
     * @param currentUsername the username of the current user of the program
     */
    public void execute(String threadName, String usernameList, String currentUsername)
    {
        AddThreadInputData inputData = new AddThreadInputData(threadName, usernameList, currentUsername);
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
