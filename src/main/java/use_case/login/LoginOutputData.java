package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData
{

    private final String username;
    private final String message;

    public LoginOutputData(String username, String message)
    {
        this.username = username;
        this.message = message;
    }

    public final String getUsername()
    {
        return username;
    }

    public final String getMessage()
    {
        return message;
    }

}
