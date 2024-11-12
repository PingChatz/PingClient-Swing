package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    @Override
    public void prepareSuccessView(LoginOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
