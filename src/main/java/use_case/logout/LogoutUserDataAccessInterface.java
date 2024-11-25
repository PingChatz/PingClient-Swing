package use_case.logout;

/**
 * DAO for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface
{

    /**
     * Logs the user out of the program (deletes their token).
     */
    void logout();
}
