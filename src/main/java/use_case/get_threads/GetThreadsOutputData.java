package use_case.get_threads;

import java.util.List;

import entity.Thread;

/**
 * Output data for get threads use case.
 */
public class GetThreadsOutputData
{
    private final List<Thread> threads;

    public GetThreadsOutputData(List<Thread> threads)
    {
        this.threads = threads;
    }

    public final List<Thread> getThreads()
    {
        return threads;
    }

}
