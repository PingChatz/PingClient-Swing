package use_case.get_threads;
import java.util.List;
import entity.Thread;

public class GetThreadsOutputData {
    private List<Thread> threads;

    public GetThreadsOutputData(List<Thread> threads) {
        this.threads = threads;
    }

    public List<Thread> getThreads() {
        return threads;
    }

}