package use_case.send_message;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Message;
import entity.MessageFactory;

/**
 * Class for testing the Send Message Interactor.
 */
class SendMessageInteractorTest
{
    private MessageFactory messageFactory;
    private SendMessageInputData validInputData;
    private SendMessageInputData invalidInputDataEmptyContent;
    private SendMessageInputData invalidInputDataContentTooLong;

    @BeforeEach
    void setUp()
    {
        // Setup mock message factory
        messageFactory = new MessageFactory();

        // Setup valid input data
        validInputData = new SendMessageInputData(
                "How's it going chumps?", 1234L, "TestUser");

        // Setup invalid input data
        invalidInputDataEmptyContent = new SendMessageInputData(
                "", 1234L, "TestJoe");
        invalidInputDataContentTooLong = new SendMessageInputData(
                "C".repeat(Message.MESSAGE_MAX_LENGTH + 1), 1234L, "TestUser");
    }

    @Test
    void testExecuteValidInputSuccess()
    {
        // Create mock DAO for this specific test
        SendMessageMessageDataAccessInterface mockServer = new InMemoryMessageDataAccess();

        // Create mock presenter for this specific test
        SendMessageOutputBoundary sendMessagePresenter = new SendMessageOutputBoundary()
        {
            @Override
            public void prepareSuccessView(SendMessageOutputData outputData)
            {
                assertEquals("How's it going chumps?", outputData.getContent());
                assertEquals("TestUser", outputData.getSenderUsername());
                assertEquals("456786765644", outputData.getTimestamp());
                assertFalse(outputData.isUseCaseFailed());
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                fail("Failure is unexpected here.");
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here.");
            }
        };

        // Create interactor for this specific test
        SendMessageInputBoundary interactor =
                new SendMessageInteractor(mockServer, messageFactory, sendMessagePresenter);

        // Assert
        interactor.execute(validInputData);
    }

    @Test
    void testExecuteServerServerError()
    {
        // Create mock DAO for this specific test
        SendMessageMessageDataAccessInterface mockServer = new InMemoryMessageDataAccessWithServerError();

        // Create mock presenter for this specific test
        SendMessageOutputBoundary sendMessagePresenter = new SendMessageOutputBoundary()
        {
            @Override
            public void prepareSuccessView(SendMessageOutputData outputData)
            {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("An unexpected error occurred: Server Error", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here.");
            }
        };

        // Create interactor for this specific test
        SendMessageInputBoundary interactor =
                new SendMessageInteractor(mockServer, messageFactory, sendMessagePresenter);

        // Assert
        interactor.execute(validInputData);
    }

    @Test
    void testExecuteServerClientError()
    {
        // Create mock DAO for this specific test
        SendMessageMessageDataAccessInterface mockServer = new InMemoryMessageDataAccessWithClientError();

        // Create mock presenter for this specific test
        SendMessageOutputBoundary sendMessagePresenter = new SendMessageOutputBoundary()
        {
            @Override
            public void prepareSuccessView(SendMessageOutputData outputData)
            {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("Some of your inputs are invalid.", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here.");
            }
        };

        // Create interactor for this specific test
        SendMessageInputBoundary interactor =
                new SendMessageInteractor(mockServer, messageFactory, sendMessagePresenter);

        // Assert
        interactor.execute(validInputData);
    }

    @Test
    void testExecuteEmptyContent()
    {
        // Create mock DAO for this specific test
        SendMessageMessageDataAccessInterface mockServer = new InMemoryMessageDataAccess();

        // Create mock presenter for this specific test
        SendMessageOutputBoundary sendMessagePresenter = new SendMessageOutputBoundary()
        {
            @Override
            public void prepareSuccessView(SendMessageOutputData outputData)
            {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("Message field is empty.", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here.");
            }
        };

        // Create interactor for this specific test
        SendMessageInputBoundary interactor =
                new SendMessageInteractor(mockServer, messageFactory, sendMessagePresenter);

        // Assert
        interactor.execute(invalidInputDataEmptyContent);
    }

    @Test
    void testExecuteContentTooLong()
    {
        // Create mock DAO for this specific test
        SendMessageMessageDataAccessInterface mockServer = new InMemoryMessageDataAccess();

        // Create mock presenter for this specific test
        SendMessageOutputBoundary sendMessagePresenter = new SendMessageOutputBoundary()
        {
            @Override
            public void prepareSuccessView(SendMessageOutputData outputData)
            {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("Message is too long. Must be under "
                        + Message.MESSAGE_MAX_LENGTH + " characters.", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here.");
            }
        };

        // Create interactor for this specific test
        SendMessageInputBoundary interactor =
                new SendMessageInteractor(mockServer, messageFactory, sendMessagePresenter);

        // Assert
        interactor.execute(invalidInputDataContentTooLong);
    }

    @Test
    void testSwitchToThreadsView()
    {
        // Create mock DAO for this specific test
        SendMessageMessageDataAccessInterface mockServer = new InMemoryMessageDataAccess();

        // Create mock presenter for this specific test
        SendMessageOutputBoundary sendMessagePresenter = new SendMessageOutputBoundary()
        {
            @Override
            public void prepareSuccessView(SendMessageOutputData outputData)
            {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                fail("Failure is unexpected here.");
            }

            @Override
            public void switchToThreadsView()
            {
                assertTrue(true);
            }
        };

        // Create interactor for this specific test
        SendMessageInputBoundary interactor =
                new SendMessageInteractor(mockServer, messageFactory, sendMessagePresenter);

        // Assert
        interactor.switchToThreadsView();
    }

    // == MOCK DAOs FOR TESTING ==

    /**
     * Mock class, mimics a functional server.
     */
    private static final class InMemoryMessageDataAccess implements SendMessageMessageDataAccessInterface
    {
        @Override
        public Message save(Message message, Long threadID)
        {
            return new Message(message.getContent(), message.getSenderUsername(), "456786765644");
        }
    }

    /**
     * Mock class, mimics a functional server that is given invalid inputs.
     */
    private static final class InMemoryMessageDataAccessWithClientError implements SendMessageMessageDataAccessInterface
    {
        @Override
        public Message save(Message message, Long threadID) throws IOException
        {
            throw new IOException("Some of your inputs are invalid.");
        }
    }

    /**
     * Mock class, mimics a dysfunctional server.
     */
    private static final class InMemoryMessageDataAccessWithServerError implements SendMessageMessageDataAccessInterface
    {
        @Override
        public Message save(Message message, Long threadID)
        {
            throw new RuntimeException("Server Error");
        }
    }
}
