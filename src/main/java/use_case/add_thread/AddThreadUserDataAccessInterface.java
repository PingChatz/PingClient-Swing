package use_case.add_thread;

/**
 * DAO for the Add Thread Use Case ; for the Thread.
 * Contains all necessary methods to create a new thread.
 */
public interface AddThreadUserDataAccessInterface
{
    /**
     * Checks if the given username  exists.
     *
     * @param threadName the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String threadName);
}

