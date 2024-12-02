package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_thread.AddThreadViewModel;
import interface_adapter.send_message.ChatViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.threads.GetThreadsController;
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
    private final ChatViewModel chatViewModel;
    private final AddThreadViewModel addThreadViewModel;
    private final GetThreadsController getThreadsController;

    public LoginPresenter(
            LoginViewModel loginViewModel,
            ViewManagerModel viewManagerModel,
            SignupViewModel signupViewModel,
            ThreadsViewModel threadsViewModel,
            ChatViewModel chatViewModel,
            AddThreadViewModel addThreadViewModel,
            GetThreadsController getThreadsController)
    {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.threadsViewModel = threadsViewModel;
        this.chatViewModel = chatViewModel;
        this.addThreadViewModel = addThreadViewModel;
        this.getThreadsController = getThreadsController; // Initialize the controller
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

        // Update the Current Username in each ViewModel
        threadsViewModel.getState().setCurrentUsername(outputData.getUsername());
        threadsViewModel.firePropertyChanged();

        chatViewModel.getState().setCurrentUsername(outputData.getUsername());
        chatViewModel.firePropertyChanged();

        addThreadViewModel.getState().setCurrentUsername(outputData.getUsername());
        addThreadViewModel.firePropertyChanged();

        // Switch to the ThreadsView
        viewManagerModel.setState(threadsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        // Automatically refresh threads
        getThreadsController.execute(outputData.getUsername());
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
    public void switchToSignUpView()
    {
        //Switch to the SignUpView
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
