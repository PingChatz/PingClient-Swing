package interface_adapter.threads;

import use_case.get_threads.GetThreadsInputBoundary;

/**
 * This is the controller in the clean architecture of the threads view use case.
 */
public class GetThreadsController
{
    // == INSTANCE VARIABLES ==
    private final GetThreadsInputBoundary getThreadsUseCaseInteractor;

    // == CONSTRUCTOR ==
    public GetThreadsController(GetThreadsInputBoundary getThreadsUseCaseInteractor)
    {
        this.getThreadsUseCaseInteractor = getThreadsUseCaseInteractor;
    }

    /**
     * Executes the "get threads" Use Case.
     */
    public void execute()
    {
        this.getThreadsUseCaseInteractor.execute(); //triggering the GetThreads Use case
    }

    /**
     * This switches the current view to the chat view (if the user presses a thread).
     * @param threadID the ID of the thread/chat to switch into
     */
    public void switchToChatView(Long threadID)
    {
        this.getThreadsUseCaseInteractor.switchToChatView(threadID);
    }
}
