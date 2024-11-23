package entity;

/**
 * Factory for creating users.
 */
public class UserFactory
{

    /**
     * This method creates a new user object.
     * @param name of user
     * @param password password of the user
     * @param email email of the user
     * @return a new user object
     */
    public User create(String name, String email, String password)
    {
        return new User(name, email, password);
    }
}
