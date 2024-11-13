package use_case.login;

/**
 * The Input Data for the Login Use Case.
 */
// TODO: overwrite with our code
public final class LoginInputData
{

    private final String username;
    private final String password;

    public LoginInputData(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

}
