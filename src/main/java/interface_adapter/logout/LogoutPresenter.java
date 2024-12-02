package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import interface_adapter.threads.ThreadsState;
import interface_adapter.threads.ThreadsViewModel;
import use_case.logout.LogoutOutputBoundary;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary
{

    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final ThreadsViewModel threadsViewModel;
    private final ChatViewModel chatViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel,
                           ThreadsViewModel threadsViewModel, ChatViewModel chatViewModel)
    {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.threadsViewModel = threadsViewModel;
        this.chatViewModel = chatViewModel;
    }

    @Override
    public void prepareSuccessView()
    {
        // 1. get the LoggedInState out of the appropriate View Model,
        final ThreadsState threadsState = threadsViewModel.getState();
        // 2. set the username in the state to the empty string
        threadsState.resetThreadData();
        // 3. set the state in the LoggedInViewModel to the updated state
        threadsViewModel.setState(threadsState);
        // 4. firePropertyChanged so that the View that is listening is updated.
        threadsViewModel.firePropertyChanged("logged-out");

        // delete the information in the chat state
        final ChatState chatState = chatViewModel.getState();
        chatState.resetChatState();
        chatViewModel.setState(chatState);
        chatViewModel.firePropertyChanged();

        // 5. get the LoginState out of the appropriate View Model,
        final LoginState loginState = loginViewModel.getState();
        // 6. set the username and password in the state to the empty string
        loginState.setUsernameOrEmail("");
        loginState.setPassword("");
        // 7. set the state in the LoginViewModel to the updated state
        loginViewModel.setState(loginState);
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
    }
}
