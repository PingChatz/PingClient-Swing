package use_case.add_thread;

/**
 * The output boundary for the Thread Use Case.
 */
public interface AddThreadOutputBoundary
{
    /**
     * Prepares the success view for the Thread Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(AddThreadOutputData outputData);

    /**
     * Prepares the failure view for the Thread Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to Threads View.
     */
    void switchToThreadsView();
}
