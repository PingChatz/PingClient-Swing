package use_case.signup;

/**
 * Input Boundary for actions which are related to signing up.
 */
// TODO: overwrite with our code
public interface SignupInputBoundary
{
    /**
     * Executes the signup use case.
     *
     * @param signupInputData the input data
     */
    void execute(SignupInputData signupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to home page view use case.
     */
    void switchToHomePageView();
}
