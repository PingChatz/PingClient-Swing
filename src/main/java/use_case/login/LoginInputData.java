package use_case.login;

/**
 * The Input Data for the Login Use Case.
 */
public final class LoginInputData
{

    private final String usernameOrEmail;
    private final String password;

    public LoginInputData(String usernameOrEmail, String password)
    {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail()
    {
        return usernameOrEmail;
    }

    public String getPassword()
    {
        return password;
    }

}
