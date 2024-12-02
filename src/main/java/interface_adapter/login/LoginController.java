package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The controller for the Login Use Case.
 */
public class LoginController
{

    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor)
    {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     *
     * @param usernameOrEmail the username or email of the user logging in
     * @param password        the password of the user logging in
     */
    public void execute(String usernameOrEmail, String password)
    {
        final LoginInputData loginInputData = new LoginInputData(usernameOrEmail, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    /**
     * Switches to sign up view.
     */
    public void switchToSignUpView()
    {
        this.loginUseCaseInteractor.switchToSignUpView();
    }
}
