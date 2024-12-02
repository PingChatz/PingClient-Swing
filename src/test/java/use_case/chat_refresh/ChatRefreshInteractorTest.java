package use_case.chat_refresh;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import entity.Message;
import use_case.chat_refresh.*;

public class ChatRefreshInteractorTest
{

    @Test
    public void testExecuteSuccess()
    {
        // Arrange
        Long threadID = 1L;
        ChatRefreshInputData inputData = new ChatRefreshInputData(threadID);

        // Create a mock thread data access object
        MockChatRefreshThreadDataAccessInterface mockThreadDataAccess = new MockChatRefreshThreadDataAccessInterface();
        mockThreadDataAccess.setMessages(Arrays.asList(
                new Message("Hello", "user1", "2021-10-01 10:00:00"),
                new Message("Hi", "user2", "2021-10-01 10:01:00")
        ));

        // Create a mock output boundary
        MockChatRefreshOutputBoundary mockOutputBoundary = new MockChatRefreshOutputBoundary();

        // Create interactor
        ChatRefreshInteractor interactor = new ChatRefreshInteractor(mockThreadDataAccess, mockOutputBoundary);

        // Act
        interactor.execute(inputData);

        // Assert
        // Check that prepareSuccessView was called with correct data
        assertTrue(mockOutputBoundary.successCalled);
        List<String[]> expectedMessages = Arrays.asList(
                new String[]{"user1", "Hello", "2021-10-01 10:00:00"},
                new String[]{"user2", "Hi", "2021-10-01 10:01:00"}
        );
        assertEquals(expectedMessages.size(), mockOutputBoundary.outputData.getMessages().size());
        for (int i = 0; i < expectedMessages.size(); i++) {
            assertArrayEquals(expectedMessages.get(i), mockOutputBoundary.outputData.getMessages().get(i));
        }

        // Ensure that prepareFailView was not called
        assertFalse(mockOutputBoundary.failCalled);
    }

    @Test
    public void testExecuteException() {
        // Arrange
        Long threadID = 1L;
        ChatRefreshInputData inputData = new ChatRefreshInputData(threadID);

        // Create a mock thread data access object that throws an exception
        MockChatRefreshThreadDataAccessInterface mockThreadDataAccess = new MockChatRefreshThreadDataAccessInterface();
        mockThreadDataAccess.setThrowException(true);

        // Create a mock output boundary
        MockChatRefreshOutputBoundary mockOutputBoundary = new MockChatRefreshOutputBoundary();

        // Create interactor
        ChatRefreshInteractor interactor = new ChatRefreshInteractor(mockThreadDataAccess, mockOutputBoundary);

        // Act
        interactor.execute(inputData);

        // Assert
        // Check that prepareFailView was called with correct error message
        assertTrue(mockOutputBoundary.failCalled);
        assertTrue(mockOutputBoundary.errorMessage.contains("Simulated exception"));

        // Ensure that prepareSuccessView was not called
        assertFalse(mockOutputBoundary.successCalled);
    }

    @Test
    public void testExecuteNoMessages() {
        // Arrange
        Long threadID = 1L;
        ChatRefreshInputData inputData = new ChatRefreshInputData(threadID);

        // Create a mock thread data access object that returns empty list
        MockChatRefreshThreadDataAccessInterface mockThreadDataAccess = new MockChatRefreshThreadDataAccessInterface();
        mockThreadDataAccess.setMessages(Collections.emptyList());

        // Create a mock output boundary
        MockChatRefreshOutputBoundary mockOutputBoundary = new MockChatRefreshOutputBoundary();

        // Create interactor
        ChatRefreshInteractor interactor = new ChatRefreshInteractor(mockThreadDataAccess, mockOutputBoundary);

        // Act
        interactor.execute(inputData);

        // Assert
        // Check that prepareSuccessView was called with empty messages
        assertTrue(mockOutputBoundary.successCalled);
        assertEquals(0, mockOutputBoundary.outputData.getMessages().size());

        // Ensure that prepareFailView was not called
        assertFalse(mockOutputBoundary.failCalled);
    }

    // Mock classes
    private class MockChatRefreshThreadDataAccessInterface implements ChatRefreshThreadDataAccessInterface {
        private List<Message> messages;
        private boolean throwException = false;

        public void setMessages(List<Message> messages) {
            this.messages = messages;
        }

        public void setThrowException(boolean throwException) {
            this.throwException = throwException;
        }

        @Override
        public List<Message> getMessages(Long threadID) throws Exception {
            if (throwException) {
                throw new Exception("Simulated exception");
            }
            return messages;
        }
    }

    private class MockChatRefreshOutputBoundary implements ChatRefreshOutputBoundary {
        public boolean successCalled = false;
        public boolean failCalled = false;
        public ChatRefreshOutputData outputData;
        public String errorMessage;

        @Override
        public void prepareSuccessView(ChatRefreshOutputData outputData) {
            this.successCalled = true;
            this.outputData = outputData;
        }

        @Override
        public void prepareFailView(String errorMessage) {
            this.failCalled = true;
            this.errorMessage = errorMessage;
        }
    }
}
