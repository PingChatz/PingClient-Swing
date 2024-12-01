package use_case.get_threads;

public interface GetThreadsInputBoundary {

    void execute(GetThreadsInputData inputData);

    void switchToChatView(Long threadID);
}
