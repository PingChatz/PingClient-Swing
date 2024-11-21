package interface_adapter.send_message;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the current state of the Chat View Model.
 * PRECONDITION: all sub-arrays of allMessages are of length 2, the first (a String) being the sender's username
 *               and the second (an Object) being the message content.
 */
public class ChatState
{
    private String messageInput = "";
    private String sendMessageError;
    private String currentThreadName = "";
    private Long currentThreadID;
    private List<Object[]> allMessages = new ArrayList<>();

    public final String getMessageInput()
    {
        return messageInput;
    }

    public final void setMessageInput(String messageInput)
    {
        this.messageInput = messageInput;
    }

    public final String getSendMessageError()
    {
        return sendMessageError;
    }

    public final void setSendMessageError(String sendMessageError)
    {
        this.sendMessageError = sendMessageError;
    }

    public final String getCurrentThreadName()
    {
        return currentThreadName;
    }

    public final void setCurrentThreadName(String currentThreadName)
    {
        this.currentThreadName = currentThreadName;
    }

    public final Long getCurrentThreadID()
    {
        return currentThreadID;
    }

    public final void setCurrentThreadID(Long currentThreadID)
    {
        this.currentThreadID = currentThreadID;
    }

    public final List<Object[]> getAllMessages()
    {
        return allMessages;
    }

    public final void setAllMessages(List<Object[]> updatedAllMessages)
    {
        this.allMessages = updatedAllMessages;
    }

    @Override
    public String toString()
    {
        return "ChatState{"
                + "messageInput='" + messageInput + '\''
                + ", allMessages='" + allMessages.toString() + '\''
                + "currentThreadID='" + currentThreadID + '\''
                + "currentThreadName='" + currentThreadName + '\''
                + '}';
    }

    /**
     * Adds a message to allMessages.
     * @param username the sender of the message (always the current user)
     * @param messageContent the message content
     */
    // TODO: test this to make sure it works, in particular check the order of the pairs in the View classes
    public void addMessage(String username, Object messageContent)
    {
        Object[] newMessageTuple = new Object[2];
        newMessageTuple[0] = username;
        newMessageTuple[1] = messageContent;

        this.allMessages.add(newMessageTuple);
    }
}
