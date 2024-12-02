package use_case.chat_refresh;

/**
 * The output boundary for the Chat Refresh Use Case.
 */
public interface ChatRefreshOutputBoundary
{
    /**
     * Prepares the success view for the Chat Refresh Use Case.
     * @param outputData the output data for this use case
     */
    void prepareSuccessView(ChatRefreshOutputData outputData);

    /**
     * Prepares the fail  view for the Chat Refresh Use Case.
     * @param errorMessage the error message to present
     */
    void prepareFailView(String errorMessage);
}
