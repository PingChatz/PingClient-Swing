package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData
{

    private final String username;
    private final String email;
    private final String password;
    private final String repeatPassword;

    public SignupInputData(String username, String email, String password, String repeatPassword)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;

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
