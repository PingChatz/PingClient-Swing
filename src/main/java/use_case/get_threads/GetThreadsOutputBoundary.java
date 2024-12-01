package use_case.get_threads;

public interface GetThreadsOutputBoundary {
    void prepareSuccessView(GetThreadsOutputData getThreadsOutputData);
    void prepareFailView(String errorMessage);
    void switchToChatView(Long threadID);

    void switchToAddThreadView();
}
