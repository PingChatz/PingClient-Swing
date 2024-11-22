package use_case.get_threads;

public class GetThreadsOutputData
{
    private Long threads;
    private boolean useCaseFailed;

    public GetThreadsOutputData(Long threads, boolean useCaseFailed)
    {
        this.threads = threads;
        this.useCaseFailed = useCaseFailed;
    }

    public Long getThreadIDs()
    {
        return threads;
    }

    public boolean isUseCaseFailed()
    {
        return useCaseFailed;
    }
}
