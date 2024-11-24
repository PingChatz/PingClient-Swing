package interface_adapter.send_message;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the current state of the Chat View Model.
 * PRECONDITION: all sub-arrays of allMessages are of length 2, the first String being the sender's username
 *               and the second being the message content.
 */
public class ChatState
{
    private String messageInput = "";
    private String sendMessageError;
    private String currentThreadName = "";
    private Long currentThreadID;
    private List<String[]> allMessages = new ArrayList<>();

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

    public final List<String[]> getAllMessages()
    {
        return allMessages;
    }

    public final void setAllMessages(List<String[]> updatedAllMessages)
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
    public void addMessage(String username, String messageContent)
    {
        String[] newMessageTuple = new String[2];
        newMessageTuple[0] = username;
        newMessageTuple[1] = messageContent;

        this.allMessages.add(newMessageTuple);
    }
}
