package use_case.login;

import org.json.JSONObject;

import entity.User;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface
{

    /**
     * Checks if the given username exists.
     *
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Checks if the given user exists.
     *
     * @param username the email of the user to look for
     * @param password the password of the user to look for
     * @return JSONObject containing the authentication token, username, and any messages or errors.
     */
    JSONObject validateCredentials(String username, String password);

    /**
     * Saves the user.
     *
     * @param user the user to save
     */
    void save(User user) throws Exception;

    /**
     * Returns the user with the given username.
     *
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Returns the username of the curren user of the application.
     *
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Sets the username indicating who is the current user of the application.
     *
     * @param username the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUsername(String username);
}
