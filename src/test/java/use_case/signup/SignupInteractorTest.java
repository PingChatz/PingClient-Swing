package use_case.signup;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest
{

    private InMemoryUserDataAccess inMemoryDataAccess;
    private StubSignupPresenter stubSignupPresenter;
    private SignupInteractor interactor;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        inMemoryDataAccess = new InMemoryUserDataAccess();
        stubSignupPresenter = new StubSignupPresenter();
        userFactory = new UserFactory();
        interactor = new SignupInteractor(inMemoryDataAccess, stubSignupPresenter, userFactory);
    }

    @Test
    void testEmptyUsername() {
        SignupInputData inputData = new SignupInputData("", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testInvalidUsernameFormat() {
        SignupInputData inputData = new SignupInputData("us", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username must be 3-15 characters and contain only letters, numbers, or underscores.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testEmptyEmail() {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Email cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testInvalidEmailFormat() {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "invalid-email");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Please enter a valid email address.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testEmptyPassword() {
        SignupInputData inputData = new SignupInputData("username", "", "", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Password cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testPasswordTooShort() {
        SignupInputData inputData = new SignupInputData("username", "pass", "pass", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Password must be at least 8 characters long.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testPasswordsDontMatch() {
        SignupInputData inputData = new SignupInputData("username", "password123", "password321", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Passwords don't match.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testSuccessfulSignup() {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNotNull(stubSignupPresenter.successData);
        assertEquals("username", stubSignupPresenter.successData.getUsername());
        assertFalse(stubSignupPresenter.successData.isUseCaseFailed());
        assertNull(stubSignupPresenter.errorMessage);
    }

    @Test
    void testDaoExceptionDuringSave() {
        inMemoryDataAccess.setThrowException(true);

        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("An error occurred during sign-up. Please try again later.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testSwitchToLoginView() {
        interactor.switchToLoginView();

        assertTrue(stubSignupPresenter.switchToLoginCalled);
    }

    // In-Memory User Data Access Object
    static class InMemoryUserDataAccess implements SignupUserDataAccessInterface {
        private final java.util.Map<String, User> database = new java.util.HashMap<>();
        private boolean throwException = false;

        void setThrowException(boolean value) {
            this.throwException = value;
        }

        @Override
        public boolean existsByName(String username) {
            return database.containsKey(username);
        }

        @Override
        public void save(User user) throws Exception {
            if (throwException) {
                throw new Exception("Simulated database exception");
            }
            database.put(user.getUsername(), user);
        }
    }

    // Stub Presenter Implementation
    static class StubSignupPresenter implements SignupOutputBoundary {
        SignupOutputData successData = null;
        String errorMessage = null;
        boolean switchToLoginCalled = false;

        @Override
        public void switchToHomePageView() {
            // Empty implementation for testing purposes
        }

        @Override
        public void prepareSuccessView(SignupOutputData outputData) {
            this.successData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void switchToLoginView() {
            this.switchToLoginCalled = true;
        }
    }
}
