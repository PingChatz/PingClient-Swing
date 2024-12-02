package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData
{

    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String email;

    public SignupInputData(String username, String password, String repeatPassword, String email)
    {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
    }

    public final String getUsername()
    {
        return username;
    }

    public final String getPassword()
    {
        return password;
    }

    public final String getRepeatPassword()
    {
        return repeatPassword;
    }

    public final String getEmail()
    {
        return email;
    }
}
