package use_case.add_thread;

import entity.Message;
import entity.Thread;
import entity.ThreadFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing the Add Thread Interactor.
 */
class AddThreadInteractorTest
{
    private ThreadFactory threadFactory;

    private AddThreadInputData validInputData;
    private AddThreadInputData invalidInputDataEmptyName;
    private AddThreadInputData invalidInputDataNameTooLong;
    private AddThreadInputData invalidInputDataNameTooShort;
    private AddThreadInputData invalidInputDataEmptyUsers;
    private AddThreadInputData invalidInputDataPoorlyFormattedUsers;

    @BeforeEach
    void setUp()
    {
        // Setup mock thread factory
        threadFactory = new ThreadFactory();

        // Setup valid input data
        validInputData = new AddThreadInputData(
                "Valid Thread", "user1,user2,user3", "TestJoe");

        // Setup invalid input data
        invalidInputDataEmptyName = new AddThreadInputData(
                "", "user1,user2", "TestJoe");
        invalidInputDataNameTooLong = new AddThreadInputData(
                "A".repeat(Thread.THREAD_NAME_MAX_LENGTH + 1),
                "user1,user2", "TestJoe");
        invalidInputDataNameTooShort = new AddThreadInputData(
                "A", "user1,user2", "TestJoe");
        invalidInputDataEmptyUsers = new AddThreadInputData(
                "Valid Thread", "", "TestJoe");
        invalidInputDataPoorlyFormattedUsers = new AddThreadInputData(
                "Valid Thread", "user1,,user2", "TestJoe");
    }

    @Test
    void testExecuteValidInputSuccess()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();

        // create mock presenter for this specific test
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                assertEquals("Valid Thread", outputData.getThreadName());
                assertEquals(3456L, outputData.getThreadID());
                assertEquals("New thread 'Valid Thread' has been successfully created.", successMessage);
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                fail("Failure is unexpected here");
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here");
            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.execute(validInputData);
    }

    @Test
    void testExecuteEmptyThreadName()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();

        // create mock presenter for this specific test
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                fail("Success is unexpected here");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("Thread name field is empty.", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here");
            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.execute(invalidInputDataEmptyName);
    }

    @Test
    void testExecuteThreadNameTooLong()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();

        // create mock presenter for this specific test
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                fail("Success is unexpected here");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("Thread name is too long. Must be under "
                        + Thread.THREAD_NAME_MAX_LENGTH + " characters.", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here");
            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.execute(invalidInputDataNameTooLong);
    }

    @Test
    void testExecuteThreadNameTooShort()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();

        // create mock presenter for this specific test
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                fail("Success is unexpected here");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("Thread name is too short. Must be at least "
                        + Thread.THREAD_NAME_MIN_LENGTH + " characters.", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here");
            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.execute(invalidInputDataNameTooShort);
    }

    @Test
    void testExecuteEmptyUserList()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();

        // create mock presenter for this specific test
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                fail("Success is unexpected here");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("List of Users is Empty", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here");
            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.execute(invalidInputDataEmptyUsers);
    }

    @Test
    void testExecutePoorlyFormattedUserList()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();

        // create mock presenter for this specific test
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                fail("Success is unexpected here");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("List of users is poorly formatted. \n "
                        + "(should be separated by commas and contain no spaces)", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here");
            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.execute(invalidInputDataPoorlyFormattedUsers);
    }

    @Test
    void testExecuteServerError()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccessWithError();

        // create mock presenter for this specific test
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                fail("Success is unexpected here");
            }

            @Override
            public void prepareFailView(String errorMessage)
            {
                assertEquals("Server Error", errorMessage);
            }

            @Override
            public void switchToThreadsView()
            {
                fail("Switch is unexpected here");
            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.execute(validInputData);
    }

    @Test
    void testSwitchToThreadsView()
    {
        // create mock DAO for this specific test
        AddThreadThreadDataAccessInterface mockServer = new InMemoryThreadDataAccessWithError();

        // setup the mock presenter
        AddThreadOutputBoundary addThreadPresenter = new AddThreadOutputBoundary()
        {
            @Override
            public void prepareSuccessView(AddThreadOutputData outputData, String successMessage)
            {
                fail("Success is unexpected here");
            }

            @Override
            public void prepareFailView(String error)
            {
                fail("Fail is unexpected here");
            }

            @Override
            public void switchToThreadsView()
            {
                assertTrue(true);

            }
        };

        // create mock interactor for this specific test
        AddThreadInputBoundary interactor = new AddThreadInteractor(mockServer, addThreadPresenter, threadFactory);

        // Assert
        interactor.switchToThreadsView();

    }

    // == MOCK DAOs FOR TESTING ==

    /**
     * Mock class, mocks the behaviour of a functional server.
     */
    private static final class InMemoryThreadDataAccess implements AddThreadThreadDataAccessInterface
    {
        @Override
        public Thread save(Thread thread)
        {
            List<String> updatedUserlist = new ArrayList<>(thread.getUsernameList());
            updatedUserlist.add("TestJoe");
            List<Message> emptyMessageList = new ArrayList<>();

            return new Thread(thread.getName(), updatedUserlist, emptyMessageList, 3456L);
        }
    }

    /**
     * Mock class, mocks the behaviour of a dysfunctional server.
     */
    private static final class InMemoryThreadDataAccessWithError implements AddThreadThreadDataAccessInterface
    {
        @Override
        public Thread save(Thread thread)
        {
            // TODO: based on what the server ends up outputting as an error, change this
            throw new RuntimeException("Server Error");
        }
    }
}
