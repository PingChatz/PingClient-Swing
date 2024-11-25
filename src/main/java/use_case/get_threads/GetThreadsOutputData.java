package use_case.get_threads;
import java.util.Collections;
import java.util.List;

public class GetThreadsOutputData
{
    private List<Long> threads;
    private boolean useCaseFailed;

    public GetThreadsOutputData(Long threads, boolean useCaseFailed)
    {
        this.threads = Collections.singletonList(threads);
        this.useCaseFailed = useCaseFailed;
    }

    public List<Long> getThreads()
    {
        return threads;
    }

    public boolean isUseCaseFailed()
    {
        return useCaseFailed;
    }
}
