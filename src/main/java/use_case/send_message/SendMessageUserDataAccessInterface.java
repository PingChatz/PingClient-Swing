package use_case.send_message;

/**
 * DAO for the Send Message Use Case ; for the User.
 * Contains all necessary methods to gather / change the data from / for Users relating to sending messages.
 */
// TODO: figure out what data we need for the entire send message use case from users. I think we only need
//  getCurrentUsername(), but we'll see.
public interface SendMessageUserDataAccessInterface
{
    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user
     */
    String getCurrentUsername();
}
