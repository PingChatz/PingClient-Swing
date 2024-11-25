package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.threads.ThreadsState;
import interface_adapter.threads.ThreadsViewModel;
import use_case.logout.LogoutOutputBoundary;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary
{

    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;
    private ThreadsViewModel threadsViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel, ThreadsViewModel threadsViewModel)
    {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.threadsViewModel = threadsViewModel;
    }

    @Override
    public void prepareSuccessView()
    {
        // 1. get the LoggedInState out of the appropriate View Model,
        final ThreadsState threadsState = threadsViewModel.getState();
        // 2. set the username in the state to the empty string
        threadsState.setUsername("");
        // 3. set the state in the LoggedInViewModel to the updated state
        threadsViewModel.setState(threadsState);
        // 4. firePropertyChanged so that the View that is listening is updated.
        threadsViewModel.firePropertyChanged();

        // 5. get the LoginState out of the appropriate View Model,
        final LoginState logoutState = loginViewModel.getState();
        // 6. set the username and password in the state to the empty string
        logoutState.setUsername("");
        logoutState.setPassword("");
        // 7. set the state in the LoginViewModel to the updated state
        loginViewModel.setState(logoutState);
        // 8. firePropertyChanged so that the View that is listening is updated.
        loginViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error)
    {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
