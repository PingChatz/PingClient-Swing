package use_case.get_threads;

/**
 * Output boundary for get threads use case.
 */
public interface GetThreadsOutputBoundary
{
    /**
     * Modifies necessary views if the use case is successful.
     * @param getThreadsOutputData necessary output data
     */
    void prepareSuccessView(GetThreadsOutputData getThreadsOutputData);

    /**
     * Modifies necessary views if the use case is unsuccessful.
     * @param errorMessage error message to display
     */
    void prepareFailView(String errorMessage);

    /**
     * Modifies necessary views for switching to chat view.
     * @param threadID ID of the thread to switch into
     */
    void switchToChatView(Long threadID);

    /**
     * Modifies necessary views for switching to add threads view.
     */
    void switchToAddThreadView();
}
