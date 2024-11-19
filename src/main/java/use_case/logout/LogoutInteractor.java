package use_case.logout;

/**
 * The Logout Interactor.
 */
// TODO: overwrite with our code
public class LogoutInteractor implements LogoutInputBoundary
{
    private LogoutUserDataAccessInterface userDataAccessObject;
    private LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary)
    {
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute(LogoutInputData logoutInputData)
    {
        // TODO: implement the logic of the Logout Use Case (depends on the LogoutInputData.java TODO)
        final String username = logoutInputData.getUsername();
        // * set the username to null in the DAO
        final LogoutOutputData logoutOutputData = new LogoutOutputData(username, true);
        logoutPresenter.prepareSuccessView(logoutOutputData);

    }
}

