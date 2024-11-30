package use_case.logout;

import data_access.PingBackend;
import data_access.UserDataAccessObject;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogoutInteractorTest {
    @Test
    void successTest() throws Exception {
        PingBackend backend = new PingBackend("http://pingserver-env.eba-u7hgzajj.ca-central-1.elasticbeanstalk.com/");
        LogoutUserDataAccessInterface dataAccessObject = new UserDataAccessObject(backend);

        backend.register("benj", "ben@gmail.com", "password");
        backend.login("ben@gmail.com", "password");
        //verify that a token was given to the user initially
        assertNotEquals(backend.getAccessToken(), null);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LogoutOutputBoundary successPresenter = new LogoutOutputBoundary() {
            @Override
            public void prepareSuccessView() {
                // check that the token was deleted once the user logged out
                assertNull(backend.getAccessToken());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LogoutInputBoundary interactor = new LogoutInteractor(dataAccessObject, successPresenter);
        interactor.execute();

    }
}
