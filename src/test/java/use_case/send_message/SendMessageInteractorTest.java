package use_case.send_message;

import data_access.MessageDataAccessObject;
import data_access.PingBackend;
import data_access.ThreadDataAccessObject;
import data_access.UserDataAccessObject;
import entity.*;
import entity.Thread;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Note: this class relies on the "create thread", "signup" and "login" use cases to work perfectly.
 */
public class SendMessageInteractorTest
{
    @Test
    void successTest()
    {
        // create DAOs
        PingBackend server = new PingBackend(
                "http://pingserver-env.eba-u7hgzajj.ca-central-1.elasticbeanstalk.com/");
        ThreadDataAccessObject threadDAO = new ThreadDataAccessObject(server);
        UserDataAccessObject userDAO = new UserDataAccessObject(server);
        MessageDataAccessObject messageDAO = new MessageDataAccessObject(server);

        // For the success test, we need to add two new test users to the server
        UserFactory userFactory = new UserFactory();
        User testUser = userFactory.create("TestBill", "billspassword", "bill@testemail.test");
        User testUser2 = userFactory.create("TestSteve", "stevespassword", "steve@testemail.test");
        userDAO.save(testUser);
        userDAO.save(testUser2);

        // We also need to create a new test thread in the server and record its ID
        ThreadFactory threadFactory = new ThreadFactory();
        Thread testThread = threadFactory.create("Test Thread", "TestBill,TestSteve");
        Long testThreadID = threadDAO.save(testThread).getThreadID();

        // create mock input data
        SendMessageInputData inputData = new SendMessageInputData(
                "Hello! This is a test message.", testThreadID);

        // create test Message object
        MessageFactory messageFactory = new MessageFactory();
        Message testMessage = messageFactory.create(inputData.getContent(), inputData.getThreadID());

        // This creates a custom successPresenter that tests whether the test case is as we expect.
        SendMessageOutputBoundary successPresenter = new SendMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SendMessageOutputData outputData) {
                // check that the output data contains the username
                assertEquals("TestBill", outputData.getSenderUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToThreadsView() {
                fail("Use case failure is unexpected.");
            }
        };

        // run the interactor and check that the message was logged successfully
        SendMessageInputBoundary interactor = new SendMessageInteractor(
                userDAO, messageDAO, messageFactory, successPresenter);
        interactor.execute(inputData);
    }
}

}
