package use_case.send_message;

/**
 * DAO for the Signup Use Case.
 */
// TODO: figure out what data we need for the entire send message use case.
public interface SendMessageUserDataAccessInterface
{
    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user
     */
    String getCurrentUsername();
}
