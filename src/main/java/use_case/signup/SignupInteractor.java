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

    @Override
    public void execute(SignupInputData signupInputData)
    {
        // Check if the two passwords are the same
        if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword()))
        {
            userPresenter.prepareFailView("Passwords don't match.");
        }

        // create new user
        final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getEmail(),
                signupInputData.getPassword());

        // Save user via api
        try
        {
            userDataAccessObject.save(user);
        } catch (Exception e)
        {
            userPresenter.prepareFailView(e.getMessage());
        }

        // if error doss nto occur
        final SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);

//            userDataAccessObject.save(user);
//        if (userDataAccessObject.existsByName(signupInputData.getEmail()))
//        {
//            userPresenter.prepareFailView("User already exists.");
//        }
//        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword()))
//        {
//            userPresenter.prepareFailView("Passwords don't match.");
//        }
//        else
//        {
//            final User user = userFactory.create(signupInputData.getEmail(), signupInputData.getPassword(),
//                    signupInputData.getEmail());
//            userDataAccessObject.save(user);
//
//            final SignupOutputData signupOutputData = new SignupOutputData(user.getEmail(), false);
//            userPresenter.prepareSuccessView(signupOutputData);
//        }

    }

    @Override
    public void switchToLoginView()
    {
        userPresenter.switchToLoginView();
    }
}
