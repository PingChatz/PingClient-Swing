package interface_adapter.login;

/**
 * The state for the Login View Model.
 */
public final class LoginState
{
    private String usernameOrEmail = "";
    private String loginError;
    private String password = "";

    public String getUsernameOrEmail()
    {
        return usernameOrEmail;
    }

    public String getLoginError()
    {
        return loginError;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {this.usernameOrEmail =usernameOrEmail;}

    public void setLoginError(String usernameError) {this.loginError = usernameError;}

    public void setPassword(String password)
    {
        this.password = password;
    }

}
