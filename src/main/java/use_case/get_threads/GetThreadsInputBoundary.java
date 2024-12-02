package use_case.get_threads;

/**
 * Input Boundary for actions which are related to getting threads.
 */
public interface GetThreadsInputBoundary
{

    /**
     * Executes get threads use case.
     * @param inputData the input data
     */
    void execute(GetThreadsInputData inputData);

    /**
     * Executes switch to chat view.
     * @param threadID the thread ID of the thread to switch to
     */
    void switchToChatView(Long threadID);

    /**
     * Executes switch to add thread view.
     */
    void switchToAddThreadView();
}
