package interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
// TODO: overwrite with our code
public final class SignupState
{
    private String username = "";
    private String usernameError;
    private String email;
    private String emailError;
    private String password = "";
    private String passwordError;
    private String repeatPassword = "";
    private String repeatPasswordError;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsernameError()
    {
        return usernameError;
    }

    public void setUsernameError(String usernameError)
    {
        this.usernameError = usernameError;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordError()
    {
        return passwordError;
    }

    public void setPasswordError(String passwordError)
    {
        this.passwordError = passwordError;
    }

    public String getRepeatPassword()
    {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
    }

    public String getRepeatPasswordError()
    {
        return repeatPasswordError;
    }

    public void setRepeatPasswordError(String repeatPasswordError)
    {
        this.repeatPasswordError = repeatPasswordError;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmailError()
    {
        return emailError;
    }

    public void setEmailError(String emailError)
    {
        this.emailError = emailError;
    }

    @Override
    public String toString()
    {
        return "SignupState{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + '}';
    }
}
