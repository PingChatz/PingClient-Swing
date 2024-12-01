package use_case.login;

import org.json.JSONObject;

/**
 * The Login Interactor.
 */
// TODO: overwrite with our code
public class LoginInteractor implements LoginInputBoundary
{
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary)
    {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData)
    {
        final String usernameOrEmail = loginInputData.getUsernameOrEmail();
        final String password = loginInputData.getPassword();

        try
        {
            JSONObject response = userDataAccessObject.validateCredentials(usernameOrEmail, password);
            if (response != null && response.has("authToken"))
            {
                String username = response.optString("username");
                String message = response.optString("message");
                LoginOutputData loginOutputData = new LoginOutputData(username, message, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            } else if (response != null && response.has("error"))
            {
                String errorMessage = response.optString("message");
                loginPresenter.prepareFailView(errorMessage);
            } else
            {
                loginPresenter.prepareFailView("Login failed");
            }
        } catch (Exception e)
        {
            loginPresenter.prepareFailView("An error occurred during login: " + e.getMessage());
        }
    }

    @Override
    public void switchToSignUpView()
    {
        loginPresenter.switchToSignUpView();
    }
}
