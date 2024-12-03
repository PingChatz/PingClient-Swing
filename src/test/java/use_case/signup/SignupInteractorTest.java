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
    void setUp()
    {
        inMemoryDataAccess = new InMemoryUserDataAccess();
        stubSignupPresenter = new StubSignupPresenter();
        userFactory = new UserFactory();
        interactor = new SignupInteractor(inMemoryDataAccess, stubSignupPresenter, userFactory);
    }

    // Existing tests...

    @Test
    void testNullUsername()
    {
        SignupInputData inputData = new SignupInputData(null, "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testUsernameOnlySpaces()
    {
        SignupInputData inputData = new SignupInputData("   ", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testInvalidUsernameCharacters()
    {
        SignupInputData inputData = new SignupInputData("user$name", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username must be 3-15 characters and contain only letters, numbers, or underscores.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testEmailOnlySpaces()
    {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "   ");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Email cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testNullPassword()
    {
        SignupInputData inputData = new SignupInputData("username", null, null, "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Password cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testSwitchToHomePageView()
    {
        interactor.switchToHomePageView();

        assertTrue(stubSignupPresenter.switchToHomePageCalled);
    }

    @Test
    void testValidEmailWithFourLetterTLD()
    {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "user@example.info");
        interactor.execute(inputData);

        assertNotNull(stubSignupPresenter.successData);
        assertEquals("username", stubSignupPresenter.successData.getUsername());
        assertFalse(stubSignupPresenter.successData.isUseCaseFailed());
        assertNull(stubSignupPresenter.errorMessage);
    }

    @Test
    void testInvalidEmailWithFiveLetterTLD()
    {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "user@example.email");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Please enter a valid email address.", stubSignupPresenter.errorMessage);
    }

    // 1. Test the switchToLoginView method
    @Test
    void testSwitchToLoginView() {
        interactor.switchToLoginView();

        assertTrue(stubSignupPresenter.switchToLoginCalled);
    }

    // 2. Test all branches in the password validation code

    @Test
    void testNullPassword1() {
        SignupInputData inputData = new SignupInputData("username", null, null, "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Password cannot be empty.", stubSignupPresenter.errorMessage);
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
        SignupInputData inputData = new SignupInputData("username", "short", "short", "email@example.com");
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

    // 3. Ensure the exception handling code is executed
    @Test
    void testDaoExceptionDuringSave() {
        inMemoryDataAccess.setThrowException(true);

        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("An error occurred during sign-up. Please try again later.", stubSignupPresenter.errorMessage);
//        assertTrue(stubSignupPresenter.exceptionPrinted);
    }

    // Additional tests for completeness

    @Test
    void testInvalidUsernameFormat() {
        SignupInputData inputData = new SignupInputData("us$", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username must be 3-15 characters and contain only letters, numbers, or underscores.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testNullUsername1() {
        SignupInputData inputData = new SignupInputData(null, "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testUsernameOnlySpaces1() {
        SignupInputData inputData = new SignupInputData("   ", "password123", "password123", "email@example.com");
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Username cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testNullEmail() {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", null);
        interactor.execute(inputData);

        assertNull(stubSignupPresenter.successData);
        assertEquals("Email cannot be empty.", stubSignupPresenter.errorMessage);
    }

    @Test
    void testEmailOnlySpaces1() {
        SignupInputData inputData = new SignupInputData("username", "password123", "password123", "   ");
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
    void testSwitchToHomePageView1() {
        interactor.switchToHomePageView();

        assertTrue(stubSignupPresenter.switchToHomePageCalled);
    }

    // In-Memory User Data Access Object
    static class InMemoryUserDataAccess implements SignupUserDataAccessInterface
    {
        private final java.util.Map<String, User> database = new java.util.HashMap<>();
        private boolean throwException = false;

        void setThrowException(boolean value)
        {
            this.throwException = value;
        }

        @Override
        public boolean existsByName(String username)
        {
            return database.containsKey(username);
        }

        @Override
        public void save(User user) throws Exception
        {
            if (throwException)
            {
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
        boolean switchToHomePageCalled = false;
        boolean exceptionPrinted = false; // New field to check if exception was printed

        @Override
        public void switchToHomePageView() {
            this.switchToHomePageCalled = true;
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

        // Override e.printStackTrace() for testing purposes
        void printStackTrace() {
            this.exceptionPrinted = true;
        }
    }
}
