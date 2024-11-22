package use_case.send_message;

/**
 * DAO for the Send Message Use Case ; for the User.
 * Contains all necessary methods to gather / change the data from / for Users relating to sending messages.
 */
public interface SendMessageUserDataAccessInterface
{
    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user
     */
    String getCurrentUsername();

    /**
     * Returns the userID of the current user of the application.
     * @return the userID of the current user
     */
    Long getCurrentUserID();
}
