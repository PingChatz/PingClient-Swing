package use_case.chat_refresh;

public interface ChatRefreshOutputBoundary
{
    /**
     * Prepares the success view for the Login Use Case.
     * output data
     */
    void prepareSuccessView(ChatRefreshOutputData outputData);
    void prepareFailView(String errorMessage);
}
