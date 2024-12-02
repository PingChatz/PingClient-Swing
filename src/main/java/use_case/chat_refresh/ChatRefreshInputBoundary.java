package use_case.chat_refresh;

/**
 * Input Boundary for actions which are related to chat refreshing.
 */
public interface ChatRefreshInputBoundary
{

    /**
     * Executes the ChatRefresh use case.
     * @param inputData the input data for this use case
     */
    void execute(ChatRefreshInputData inputData);
}
