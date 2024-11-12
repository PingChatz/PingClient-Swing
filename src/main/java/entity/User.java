package entity;

/**
 * The representation of a user in our program.
 */
public class User {

    private final Long userID = null;
    private final String username;
    private final String passwordHash = null;
    private final String email;
    // TODO: when below TODOs have been solved, remove 'null'

    public User(String username, String password, String email) {
        // TODO: generate a unique userID and a userAddress
        // TODO: hash <password> and save it locally in the "passwordHash" instance variable
        this.username = username;
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public Long getUserID() {
        return this.userID;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }
}
