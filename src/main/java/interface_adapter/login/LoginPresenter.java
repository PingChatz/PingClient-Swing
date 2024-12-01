package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.threads.ThreadsViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary
{
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final ThreadsViewModel threadsViewModel;

    public LoginPresenter(LoginViewModel loginViewModel, ViewManagerModel viewManagerModel, SignupViewModel signupViewModel,
                          ThreadsViewModel threadsViewModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.threadsViewModel = threadsViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData outputData)
    {
        //Update the LoginViewModel State
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameOrEmail(outputData.getUsername());
        loginState.setLoginError("");
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        //Switch to the ThreadsView
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        //Update the LoginViewModel State
        LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(errorMessage);
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSignUpView() {
        //Switch to the SignUpView
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
