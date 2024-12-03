package use_case.get_threads;

import entity.Thread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for GetThreadsInteractor.
 */
class GetThreadsInteractorTest {

    @Test
    void testExecuteValidInputSuccess() {
        // Mock DAO and presenter
        GetThreadsThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();
        GetThreadsOutputBoundary mockPresenter = new GetThreadsOutputBoundary() {
            @Override
            public void prepareSuccessView(GetThreadsOutputData outputData) {
                List<Thread> threads = outputData.getThreads();
                assertEquals(2, threads.size());
                assertEquals("Thread 1", threads.get(0).getName());
                assertEquals("Thread 2", threads.get(1).getName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure is unexpected here.");
            }

            @Override
            public void switchToChatView(Long threadID) {
                fail("Switch to chat view should not be invoked in this test.");
            }

            @Override
            public void switchToAddThreadView() {
                fail("Switch to Add Thread View should not be invoked in this test.");
            }
        };

        // Create and execute interactor
        GetThreadsInputBoundary interactor = new GetThreadsUseCaseInteractor(mockServer, mockPresenter);
        GetThreadsInputData validInputData = new GetThreadsInputData("testUser");

        interactor.execute(validInputData);
    }

    @Test
    void testExecuteServerError() {
        // Mock DAO and presenter
        GetThreadsThreadDataAccessInterface mockServer = new InMemoryThreadDataAccessWithError();
        GetThreadsOutputBoundary mockPresenter = new GetThreadsOutputBoundary() {
            @Override
            public void prepareSuccessView(GetThreadsOutputData outputData) {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Error occurred: Server Error", errorMessage);
            }

            @Override
            public void switchToChatView(Long threadID) {
                fail("Switch to chat view should not be invoked in this test.");
            }

            @Override
            public void switchToAddThreadView() {
                fail("Switch to Add Thread View should not be invoked in this test.");
            }
        };

        // Create and execute interactor
        GetThreadsInputBoundary interactor = new GetThreadsUseCaseInteractor(mockServer, mockPresenter);
        GetThreadsInputData validInputData = new GetThreadsInputData("testUser");

        interactor.execute(validInputData);
    }

    @Test
    void testSwitchToChatView() {
        // Mock DAO and presenter
        GetThreadsThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();
        GetThreadsOutputBoundary mockPresenter = new GetThreadsOutputBoundary() {
            @Override
            public void prepareSuccessView(GetThreadsOutputData outputData) {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure is unexpected here.");
            }

            @Override
            public void switchToChatView(Long threadID) {
                assertTrue(true);
            }

            @Override
            public void switchToAddThreadView() {
                fail("Switch to Add Thread View should not be invoked in this test.");
            }
        };

        // Create interactor
        GetThreadsInputBoundary interactor = new GetThreadsUseCaseInteractor(mockServer, mockPresenter);

        // Simulate switching to a chat view
        assertDoesNotThrow(() -> interactor.switchToChatView(1L));
    }

    @Test
    void testSwitchToAddThreadView() {
        // Mock DAO and presenter
        GetThreadsThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess();
        GetThreadsOutputBoundary mockPresenter = new GetThreadsOutputBoundary() {
            @Override
            public void prepareSuccessView(GetThreadsOutputData outputData) {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure is unexpected here.");
            }

            @Override
            public void switchToChatView(Long threadID) {
                fail("Switch to chat view should not be invoked in this test.");
            }

            @Override
            public void switchToAddThreadView() {
                assertTrue(true);
            }
        };

        // Create interactor
        GetThreadsInputBoundary interactor = new GetThreadsUseCaseInteractor(mockServer, mockPresenter);

        // Simulate switching to Add Thread view
        assertDoesNotThrow(interactor::switchToAddThreadView);
    }

    @Test
    void testExecuteNoThreadsFound() {
        // Mock DAO that returns an empty list of threads
        GetThreadsThreadDataAccessInterface mockServer = new InMemoryThreadDataAccess() {
            @Override
            public List<Thread> getThreadsByUsername(String username) {
                return new ArrayList<>(); // Simulate no threads found
            }
        };

        // Mock presenter for this specific test
        GetThreadsOutputBoundary mockPresenter = new GetThreadsOutputBoundary() {
            @Override
            public void prepareSuccessView(GetThreadsOutputData outputData) {
                fail("Success is unexpected here.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("No threads found for user: testUser", errorMessage);
            }

            @Override
            public void switchToChatView(Long threadID) {
                fail("Switch to chat view should not be invoked in this test.");
            }

            @Override
            public void switchToAddThreadView() {
                fail("Switch to Add Thread View should not be invoked in this test.");
            }
        };

        // Create interactor
        GetThreadsInputBoundary interactor = new GetThreadsUseCaseInteractor(mockServer, mockPresenter);
        GetThreadsInputData inputData = new GetThreadsInputData("testUser");

        // Execute interactor and assert
        interactor.execute(inputData);
    }

    // == MOCK DAOs FOR TESTING ==

    /**
     * Mock DAO for simulating successful data fetching.
     */
    private static class InMemoryThreadDataAccess implements GetThreadsThreadDataAccessInterface {
        @Override
        public List<Thread> getThreadsByUsername(String username) {
            if ("testUser".equals(username)) {
                List<Thread> threads = new ArrayList<>();
                threads.add(new Thread(1L, "Thread 1"));
                threads.add(new Thread(2L, "Thread 2"));
                return threads;
            }
            return new ArrayList<>();
        }
    }

    /**
     * Mock DAO for simulating a server error.
     */
    private static final class InMemoryThreadDataAccessWithError implements GetThreadsThreadDataAccessInterface {
        @Override
        public List<Thread> getThreadsByUsername(String username) {
            throw new RuntimeException("Server Error");
        }
    }
}
