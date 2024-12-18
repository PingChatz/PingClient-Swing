package app;

import data_access.MessageDataAccessObject;
import data_access.PingBackend;
import data_access.ThreadDataAccessObject;
import data_access.UserDataAccessObject;
import entity.MessageFactory;
import entity.ThreadFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_thread.AddThreadController;
import interface_adapter.add_thread.AddThreadPresenter;
import interface_adapter.add_thread.AddThreadViewModel;
import interface_adapter.chat_refresh.ChatRefreshController;
import interface_adapter.chat_refresh.ChatRefreshPresenter;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.send_message.ChatViewModel;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.send_message.SendMessagePresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.threads.GetThreadsController;
import interface_adapter.threads.GetThreadsPresenter;
import interface_adapter.threads.ThreadsViewModel;
import use_case.add_thread.AddThreadInputBoundary;
import use_case.add_thread.AddThreadInteractor;
import use_case.add_thread.AddThreadOutputBoundary;
import use_case.chat_refresh.ChatRefreshInputBoundary;
import use_case.chat_refresh.ChatRefreshInteractor;
import use_case.chat_refresh.ChatRefreshOutputBoundary;
import use_case.get_threads.GetThreadsInputBoundary;
import use_case.get_threads.GetThreadsOutputBoundary;
import use_case.get_threads.GetThreadsUseCaseInteractor;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our Clean Architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder
{

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final UserFactory userFactory = new UserFactory();
    private final MessageFactory messageFactory = new MessageFactory();
    private final ThreadFactory threadFactory = new ThreadFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // Create the Backend instance
    PingBackend pingBackend = new PingBackend("http://pingserver-env.eba-u7hgzajj.ca-central-1.elasticbeanstalk.com/");
    private final UserDataAccessObject userDataAccessObject = new UserDataAccessObject(pingBackend);
    private final ThreadDataAccessObject threadDataAccessObject = new ThreadDataAccessObject(pingBackend);
    private final MessageDataAccessObject messageDataAccessObject = new MessageDataAccessObject(pingBackend);

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private ChatViewModel chatViewModel;
    private ThreadsViewModel threadsViewModel;
    private AddThreadViewModel addThreadViewModel;
    private LoginView loginView;
    private ChatView chatView;
    private ThreadsView threadsView;
    private AddThreadView addThreadView;

    // Declare controllers as instance variables
    private ChatRefreshController chatRefreshController;
    private GetThreadsController getThreadsController;

    private SignupController signupController;
    private LoginController loginController;

    public AppBuilder()
    {
        cardPanel.setLayout(cardLayout);
    }

    // == ADD THE VIEWS ==

    public AppBuilder addHomePageView()
    {
        HomePageView homePageView = new HomePageView(loginController, signupController);
        cardPanel.add(homePageView, "HomePage");
        return this;
    }

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
     *
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
     * This Chat View will be empty at the moment and will be updated depending on which Thread is opened.
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

    /**
     * Adds the Add Threads View to the application.
     *
     * @return this builder
     */
    public AppBuilder addAddThreadsView()
    {
        addThreadViewModel = new AddThreadViewModel();
        addThreadView = new AddThreadView(addThreadViewModel);
        cardPanel.add(addThreadView, addThreadView.getViewName());
        return this;
    }

    // == ADD THE USE CASES ==

    /**
     * Adds the Send Message Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addSendMessageUseCase()
    {
        final SendMessageOutputBoundary sendMessageOutputBoundary = new SendMessagePresenter(
                viewManagerModel, chatViewModel, threadsViewModel);
        final SendMessageInputBoundary sendMessageInteractor = new SendMessageInteractor(
                messageDataAccessObject, messageFactory, sendMessageOutputBoundary);

        final SendMessageController controller = new SendMessageController(sendMessageInteractor);
        chatView.setSendMessageController(controller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addSignupUseCase()
    {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(
                viewManagerModel, signupViewModel, loginViewModel);

        final SignupInputBoundary signupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        signupController = new SignupController(signupInteractor);
        signupView.setSignupController(signupController);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * Note: Ensure that getThreadsController is initialized before this method is called.
     *
     * @return this builder
     */
    public AppBuilder addLoginUseCase()
    {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(
                loginViewModel, viewManagerModel, signupViewModel, threadsViewModel,
                chatViewModel, addThreadViewModel, getThreadsController); // Pass getThreadsController here

        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addLogoutUseCase()
    {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(
                viewManagerModel, loginViewModel, threadsViewModel, chatViewModel);

        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(
                userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        threadsView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Get Threads Use Case to the application.
     * Note: Ensure that chatRefreshController is initialized before this method is called.
     *
     * @return this builder
     */
    public AppBuilder addGetThreadsUseCase()
    {
        final GetThreadsOutputBoundary getThreadsOutputBoundary = new GetThreadsPresenter(
                viewManagerModel, chatViewModel, threadsViewModel, addThreadViewModel,
                chatRefreshController); // Pass chatRefreshController here

        final GetThreadsInputBoundary getThreadsInteractor = new GetThreadsUseCaseInteractor(
                threadDataAccessObject, getThreadsOutputBoundary);

        this.getThreadsController = new GetThreadsController(getThreadsInteractor);
        threadsView.setGetThreadsController(getThreadsController);

        return this;
    }

    /**
     * Adds the Add Thread Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addAddThreadUseCase()
    {
        final AddThreadOutputBoundary addThreadOutputBoundary = new AddThreadPresenter(
                viewManagerModel, addThreadViewModel, threadsViewModel);

        final AddThreadInputBoundary addThreadInteractor = new AddThreadInteractor(
                threadDataAccessObject, addThreadOutputBoundary, threadFactory);

        final AddThreadController controller = new AddThreadController(addThreadInteractor);
        addThreadView.setAddThreadController(controller);
        return this;
    }

    /**
     * Adds the Chat Refresh Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addChatRefreshUseCase()
    {
        final ChatRefreshOutputBoundary chatRefreshPresenter = new ChatRefreshPresenter(chatViewModel);
        final ChatRefreshInputBoundary chatRefreshInteractor = new ChatRefreshInteractor(
                messageDataAccessObject, chatRefreshPresenter);

        this.chatRefreshController = new ChatRefreshController(chatRefreshInteractor);
        chatView.setChatRefreshController(chatRefreshController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     *
     * @return the application
     */
    public JFrame build()
    {
        // Build order matters to ensure controllers are initialized before use
        this.addLoginView()
                .addSignupView()
                .addThreadsView()
                .addChatView()
                .addAddThreadsView()
                .addChatRefreshUseCase()  // Initialize chatRefreshController
                .addGetThreadsUseCase()   // Initialize getThreadsController after chatRefreshController
                .addSendMessageUseCase()
                .addSignupUseCase()
                .addLoginUseCase()        // Now that getThreadsController is initialized
                .addLogoutUseCase()
                .addAddThreadUseCase();

        final JFrame application = new JFrame("Ping Chat");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set the size of the JFrame to 500 by 400 pixels
        application.setSize(500, 400);

        // Center the window on the screen
        application.setLocationRelativeTo(null);

        // Add the card panel to the JFrame
        application.add(cardPanel);

        // Set the initial view to the SignupView
        viewManagerModel.setState("HomePage");
        viewManagerModel.firePropertyChanged();

        // Make the window visible
        application.setVisible(true);

        return application;
    }
}
