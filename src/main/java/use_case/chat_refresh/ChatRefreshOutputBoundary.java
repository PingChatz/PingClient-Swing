package use_case.chat_refresh;

public interface ChatRefreshOutputBoundary {
    /** Prepares the success view for the Login Use Case.
     @param outputData the output data
     */

    void prepareSuccessView(ChatRefreshOutputData outputData);

}
