package use_case.login;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LoginInteractor with In-Memory Database.
 */
class LoginInteractorTest {

    private InMemoryUserDataAccess inMemoryDataAccess;
    private StubLoginPresenter stubLoginPresenter;
    private LoginInteractor interactor;

    @BeforeEach
    void setUp() {
        inMemoryDataAccess = new InMemoryUserDataAccess();
        stubLoginPresenter = new StubLoginPresenter();
        interactor = new LoginInteractor(inMemoryDataAccess, stubLoginPresenter);
    }

    @Test
    void testSuccessfulLoginAfterSavingUser() {
        inMemoryDataAccess.saveUser("testUser", "securePassword", "email@example.com");

        LoginInputData inputData = new LoginInputData("testUser", "securePassword");
        interactor.execute(inputData);

        assertNotNull(stubLoginPresenter.successData);
        assertEquals("testUser", stubLoginPresenter.successData.getUsername());
        assertEquals("Login successful", stubLoginPresenter.successData.getMessage());
        assertNull(stubLoginPresenter.errorMessage);
    }

    @Test
    void testLoginFailsWithIncorrectPassword() {
        inMemoryDataAccess.saveUser("testUser", "securePassword", "email@example.com");

        LoginInputData inputData = new LoginInputData("testUser", "wrongPassword");
        interactor.execute(inputData);

        assertNull(stubLoginPresenter.successData);
        assertEquals("Invalid credentials", stubLoginPresenter.errorMessage);
    }

    @Test
    void testLoginFailsForNonexistentUser() {
        LoginInputData inputData = new LoginInputData("nonexistentUser", "password123");
        interactor.execute(inputData);

        assertNull(stubLoginPresenter.successData);
        assertEquals("Invalid credentials", stubLoginPresenter.errorMessage);
    }

    @Test
    void testLoginThrowsException() {
        inMemoryDataAccess.setThrowException(true);

        LoginInputData inputData = new LoginInputData("testUser", "securePassword");
        interactor.execute(inputData);

        assertNull(stubLoginPresenter.successData);
        assertTrue(stubLoginPresenter.errorMessage.startsWith("An error occurred during login:"));
    }

    @Test
    void testElseBranch() {
        inMemoryDataAccess.setCustomResponse(new JSONObject()
                .put("randomKey", "someValue") // random key unrelated to authToken or error
        );

        LoginInputData inputData = new LoginInputData("user@example.com", "password123");
        interactor.execute(inputData);

        // Assert: ensure the else block is executed
        assertNull(stubLoginPresenter.successData); // No success data
        assertEquals("Login failed", stubLoginPresenter.errorMessage); // default failure message
    }

    @Test
    void testSwitchToSignUpView() {
        interactor.switchToSignUpView();

        assertTrue(stubLoginPresenter.switchToSignUpCalled);
    }

    // In-Memory Database Implementation
    static class InMemoryUserDataAccess implements LoginUserDataAccessInterface {
        private final Map<String, User> database = new HashMap<>();
        private String currentUsername = null;
        private boolean throwException = false;
        private JSONObject customResponse = null;

        void saveUser(String username, String password, String email) {
            database.put(username, new User(username, password, email));
        }

        void setThrowException(boolean value) {
            throwException = value;
        }

        void setCustomResponse(JSONObject response) { // only used for testing else branch
            this.customResponse = response;
        }

        @Override
        public boolean existsByName(String username) {
            return database.containsKey(username);
        }

        @Override
        public JSONObject validateCredentials(String username, String password) {
            if (throwException) {
                throw new RuntimeException("Simulated database exception");
            }
            if (customResponse != null) {
                return customResponse; // return the custom response if set
            }

            User user = database.get(username);
            if (user != null && user.getPassword().equals(password)
            && user.getUsername().equals(username)) {
                return new JSONObject()
                        .put("authToken", "validToken")
                        .put("username", username)
                        .put("message", "Login successful");
            } else {
                return new JSONObject()
                        .put("error", true)
                        .put("message", "Invalid credentials");
            }
        }

        @Override
        public void save(User user) {
            database.put(user.getUsername(), user);
        }

        @Override
        public User get(String username) {
            return database.get(username);
        }

        @Override
        public String getCurrentUsername() {
            return currentUsername;
        }

        @Override
        public void setCurrentUsername(String username) {
            this.currentUsername = username;
        }
    }


    // Stub Presenter Implementation
    static class StubLoginPresenter implements LoginOutputBoundary {
        LoginOutputData successData = null;
        String errorMessage = null;
        boolean switchToSignUpCalled = false;

        @Override
        public void prepareSuccessView(LoginOutputData outputData) {
            this.successData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void switchToSignUpView() {
            this.switchToSignUpCalled = true;
        }
    }
}
