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
        String username = signupInputData.getUsername();
        String email = signupInputData.getEmail();
        String password = signupInputData.getPassword();
        String repeatPassword = signupInputData.getRepeatPassword();

        System.out.println(username + " " + email + " " + password + " " + repeatPassword);

        // Username validation
        if (username == null || username.trim().isEmpty())
        {
            userPresenter.prepareFailView("Username cannot be empty.");
            return;
        }
        if (!username.matches("^[a-zA-Z0-9_]{3,15}$"))
        {
            userPresenter.prepareFailView("Username must be 3-15 characters and contain only letters, numbers, or underscores.");
            return;
        }

        // Email validation
        if (email == null || email.trim().isEmpty())
        {
            userPresenter.prepareFailView("Email cannot be empty.");
            return;
        }
        if (!isValidEmail(email))
        {
            userPresenter.prepareFailView("Please enter a valid email address.");
            return;
        }

        // Password validation
        if (password == null || password.isEmpty())
        {
            userPresenter.prepareFailView("Password cannot be empty.");
            return;
        }
        if (password.length() < 8)
        {
            userPresenter.prepareFailView("Password must be at least 8 characters long.");
            return;
        }
        if (!password.equals(repeatPassword))
        {
            userPresenter.prepareFailView("Passwords don't match.");
            return;
        }

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
            // Log the exception (optional)
            e.printStackTrace();

            // Provide a generic error message to the user
            userPresenter.prepareFailView("An error occurred during sign-up. Please try again later.");
        }
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email)
    {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    }

    @Override
    public void switchToLoginView()
    {
        userPresenter.switchToLoginView();
    }
}

