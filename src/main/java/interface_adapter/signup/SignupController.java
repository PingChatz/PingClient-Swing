package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Signup Use Case.
 */
// TODO: overwrite with our code
public class SignupController
{
    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor)
    {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     *
     * @param username  the username to sign up
     * @param email     the email of the user
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String email, String password1, String password2)
    {
//        System.out.println(username + " " + email + " " + password1 + " " + password2);
        final SignupInputData signupInputData = new SignupInputData(
                username,
                password1,
                password2,
                email
        );

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView()
    {
        userSignupUseCaseInteractor.switchToLoginView();
    }

    /**
     * Executes the "switch to Home Page View" Use Case.
     */
    public void switchToHomePageView()
    {
        userSignupUseCaseInteractor.switchToHomePageView();
    }
}
