package use_case.signup;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface
{

    /**
     * Saves the user.
     *
     * @param user the user to save
     * @throws Exception if the server fails
     */
    void save(User user) throws Exception;
}
