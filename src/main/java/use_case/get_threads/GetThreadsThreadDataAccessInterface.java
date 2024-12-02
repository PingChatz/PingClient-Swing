package use_case.get_threads;

import java.util.List;

import entity.Thread;

/**
 * DAO for Get Threads Use Case ; for threads.
 */
public interface GetThreadsThreadDataAccessInterface
{

    /**
     * Get the list of threads corresponding to the given username.
     * @param username username of the current user
     * @return a list of threads
     */
    List<Thread> getThreadsByUsername(String username);

}
