package interface_adapter.threads;

import use_case.get_threads.GetThreadsInputBoundary;
import use_case.get_threads.GetThreadsInputData;

/**
 * This is the controller in the clean architecture of the threads view use case.
 */
public class GetThreadsController
{
    private final GetThreadsInputBoundary getThreadsUseCaseInteractor;

    public GetThreadsController(GetThreadsInputBoundary getThreadsUseCaseInteractor)
    {
        this.getThreadsUseCaseInteractor = getThreadsUseCaseInteractor;
    }

    /**
     * Executes the "get threads" Use Case.
     * @param username the username of the current user of the application
     */
    public void execute(String username)
    {
        GetThreadsInputData inputData = new GetThreadsInputData(username);
        getThreadsUseCaseInteractor.execute(inputData);
    }

    /**
     * This switches the current view to the chat view (if the user presses a thread).
     *
     * @param threadID the ID of the thread/chat to switch into
     */
    public void switchToChatView(Long threadID)
    {
        this.getThreadsUseCaseInteractor.switchToChatView(threadID);
    }

    /**
     * This switches the current view to the add thread view.
     */
    public void switchToAddThreadView()
    {
        this.getThreadsUseCaseInteractor.switchToAddThreadView();
    }
}
