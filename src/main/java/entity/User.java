package entity;

/**
 * The representation of a user in our program.
 */
public class User
{
    private final String username;
    private final String email;
    private final String password;

    public User(String username, String password, String email)
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username)
    {
        this.username = username;
        this.email = null;
        this.password = null;
    }

    public final String getEmail()
    {
        return this.email;
    }

    public final String getUsername()
    {
        return this.username;
    }

    public final String getPassword()
    {
        return password;
    }
}
