package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
// TODO: overwrite with our code
public class LoginOutputData
{

    private final String username;
    private final String message;
    private final boolean useCaseFailed;

    public LoginOutputData(String username, String message, boolean useCaseFailed)
    {
        this.username = username;
        this.message = message;
        this.useCaseFailed = useCaseFailed;
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
