package use_case.chat_refresh;

import use_case.logout.LogoutOutputData;

public interface ChatRefreshOutputBoundary {
    /** Prepares the success view for the Login Use Case.
     @param outputData the output data
     */

    void prepareSuccessView(LogoutOutputData outputData);

}
