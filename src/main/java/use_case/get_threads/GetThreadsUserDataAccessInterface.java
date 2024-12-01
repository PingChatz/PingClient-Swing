package use_case.get_threads;

public interface GetThreadsUserDataAccessInterface {
    /**
     * Fetch the ID of the currently authenticated user.
     * @return Current user's ID.
     */
    Long getCurrentUserID();
}
