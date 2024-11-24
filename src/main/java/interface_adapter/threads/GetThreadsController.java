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
    public GetThreadsController(GetThreadsInputBoundary userSendMessageUseCaseInteractor)
    {
        this.getThreadsUseCaseInteractor = userSendMessageUseCaseInteractor;
    }

    /**
     * Executes the "get threads" Use Case.
     */
    public void execute()
    {
        // TODO this will implement the use case logic from the use case interactor.
    }

    /**
     * This switches the current view to the chat view(if the user presses a thread).
     */
    public void switchToChatView(Long threadID)
    {
        this.getThreadsUseCaseInteractor.switchToChatView(threadID);
    }
}
