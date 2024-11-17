package app;

import java.awt.CardLayout;

import javax.swing.*;

import data_access.UserDataAccessObject;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.send_message.ChatViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.threads.ThreadsViewModel;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 * TODO: overwrite with our code
 */
public class AppBuilder
{

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new UserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final UserDataAccessObject userDataAccessObject = new UserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ChatViewModel chatViewModel;
    private ThreadsViewModel threadsViewModel;
    private LoginView loginView;
    private ChatView chatView;
    private ThreadsView threadsView;

    public AppBuilder()
    {
        cardPanel.setLayout(cardLayout);
    }

    // == ADD THE VIEWS ==

    /**
     * Adds the Signup View to the application.
     *
     * @return this builder
     */
    public AppBuilder addSignupView()
    {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     *
     * @return this builder
     */
    public AppBuilder addLoginView()
    {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Threads View to the application.
     * @return this builder
     */
    public AppBuilder addThreadsView()
    {
        threadsViewModel = new ThreadsViewModel();
        threadsView = new ThreadsView(threadsViewModel);
        cardPanel.add(threadsView, threadsView.getViewName());
        return this;
    }

    /**
     * Adds the ChatView to the application.
     *
     * @return this builder
     */
    public AppBuilder addChatView()
    {
        chatViewModel = new ChatViewModel();
        chatView = new ChatView(chatViewModel);
        cardPanel.add(chatView, chatView.getViewName());
        return this;
    }

    // == ADD THE USE CASES ==

    /**
     * Adds the Send Message Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSendMessageUseCase()
    {
        final SendMessageOutputBoundary sendMessageOutputBoundary = new SendMessagePresenter(viewManagerModel,
                chatViewModel, threadsViewModel);
        final SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(
                userDataAccessObject, sendMessageOutputBoundary);

        final SendMessageController controller = new SendMessageController(sendMessageInteractor);
        chatView.setSendMessageController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLoginUseCase()
    {
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLogoutUseCase()
    {
        return this;
    }

    // TODO: add the rest of the builder use cases here.

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     *
     * @return the application
     */
    public JFrame build()
    {
        // TODO: This will have to change as well depending on what we want to open first
        final JFrame application = new JFrame("Ping Chat");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the size of the JFrame to 500 by 400 pixels
        application.setSize(500, 400);

        // Center the window on the screen
        application.setLocationRelativeTo(null);

        // Add the card panel to the JFrame
        application.add(cardPanel);

        // Set the initial view to the LoginView
        viewManagerModel.setState(chatView.getViewName());
        viewManagerModel.firePropertyChanged();

        // Make the window visible
        application.setVisible(true);

        return application;
    }
}