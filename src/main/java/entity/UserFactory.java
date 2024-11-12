package entity;

/**
 * Factory for creating users.
 */
public class UserFactory {

    public User create(String name, String password, String email) {
        return new User(name, password, email);
    }
}
