package use_case.send_message;

/**
 * The output boundary for the Send Message Use Case.
 */
public interface SendMessageOutputBoundary
{
    /**
     * Prepares the success view for the Send Message Use Case.
     *
     * @param outputData the output data
     */
    void prepareSuccessView(SendMessageOutputData outputData);

    /**
     * Prepares the failure view for the Send Message Use Case.
     *
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to Threads View.
     */
    void switchToThreadsView();
}
