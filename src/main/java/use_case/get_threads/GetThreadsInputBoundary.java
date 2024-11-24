package use_case.get_threads;

public interface GetThreadsInputBoundary {

    void execute();

    void switchToChatView(Long threadID);
}
