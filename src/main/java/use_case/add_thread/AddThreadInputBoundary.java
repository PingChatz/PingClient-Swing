package use_case.add_thread;

/**
 * Input Boundary for actions which are related to sending related a new thread.
 */
public interface AddThreadInputBoundary
{

    /**
     * Executes the add thread use case.
     * @param addThreadInputData the input data
     */
    void execute(AddThreadInputData addThreadInputData);

    /**
     * Executes the "switch to ThreadsView" use case.
     */
    void switchToThreadsView();
}
