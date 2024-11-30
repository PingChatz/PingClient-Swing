package use_case.get_threads;

import entity.Thread;
import java.util.List;

public interface GetThreadsThreadUserDataAccessInterface {
    /**
     * Fetch all threads associated with the given user.
     * @return List of threads for the user.
     */
    List<Thread> getThreads();
}