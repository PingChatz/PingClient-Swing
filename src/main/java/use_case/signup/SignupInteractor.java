package use_case.signup;

import entity.User;
import entity.UserFactory;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary
{
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory)
    {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    public void execute(SignupInputData signupInputData)
    {
        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword()))
        {
            userPresenter.prepareFailView("Passwords don't match.");
        } else
        {
            final User user = userFactory.create(
                    signupInputData.getUsername(),
                    signupInputData.getPassword(),
                    signupInputData.getEmail()
            );

            try
            {
                // Attempt to save the user via the DAO
                userDataAccessObject.save(user);

                // On success, prepare the success view
                final SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
                userPresenter.prepareSuccessView(signupOutputData);

            } catch (Exception e)
            {
                // On failure, extract the error message and pass it to the presenter
                userPresenter.prepareFailView(e.getMessage());
            }
        }
    }

    @Override
    public void switchToLoginView()
    {
        userPresenter.switchToLoginView();
    }
}
