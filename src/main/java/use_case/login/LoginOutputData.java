package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
// TODO: overwrite with our code
public class LoginOutputData
{

    private final String username;
    private final boolean useCaseFailed;

    public LoginOutputData(String username, boolean useCaseFailed)
    {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public final String getUsername()
    {
        return username;
    }

}
