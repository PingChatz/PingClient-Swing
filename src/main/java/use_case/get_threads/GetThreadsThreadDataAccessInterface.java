package use_case.get_threads;

import entity.Thread;
import java.util.List;

public interface GetThreadsThreadDataAccessInterface {
    /**
     * Fetch all thread IDs associated with the given user.
     * @param userID The ID of the user.
     * @return List of thread IDs.
     */
    List<Long> getUserThreadIDs(Long userID);

    /**
     * Fetch all threads for the current user.
     * @return List of threads.
     */
    List<Thread> getThreads(List<Long> threadIDs);

    List<Thread> getThreadsByUsername(String username);

}
