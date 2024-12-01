package use_case.add_thread;

import entity.Thread;

/**
 * DAO for the Add Thread Use Case ; for the Thread.
 * Contains all necessary methods to create a new thread.
 */
public interface AddThreadThreadDataAccessInterface
{
    /**
     * Saves and the new thread to the server and returns a fully constructed one.
     *
     * @param thread the thread to save
     * @return a representation of the newly created thread as it exists in the server
     */
    Thread save(Thread thread) throws Exception;
}
