package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;

/**
 * The controller for the Logout Use Case.
 */
// TODO: overwrite with our code
public class LogoutController
{

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor)
    {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username)
    {
        // TODO: run the use case interactor for the logout use case
        // 1. instantiate the `LogoutInputData`, which should contain the username.
        // 2. tell the Interactor to execute.
    }
}
