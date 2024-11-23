package entity;

/**
 * The representation of a user in our program.
 */
public class User
{
    private final String username;
    private final String email;
    private final String password;

    public User(String username, String email, String password)
    {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public final String getEmail()
    {
        return this.email;
    }

    public final String getUsername()
    {
        return this.username;
    }

    public String getPassword()
    {
        return password;
    }
}
