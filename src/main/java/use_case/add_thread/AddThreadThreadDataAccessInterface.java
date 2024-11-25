package use_case.add_thread;

import entity.Thread;

/**
 * DAO for the Add Thread Use Case ; for the Thread.
 * Contains all necessary methods to create a new thread.
 */
public interface AddThreadThreadDataAccessInterface
{
    /**
     * Checks if the given thread name exists.
     * @param threadName the thread name to look for
     * @return true if a thread with the given thread name exists; false otherwise
     */
    boolean existsByName(String threadName);

    /**
     * Saves the new thread.
     * @param thread the thread to save
     */
    void save(Thread thread);
}
