package interface_adapter.send_message;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the current state of the Chat View Model.
 */
public class ChatState
{
    // TODO: figure out what other states should be saved in the ChatState.
    private String messageInput = "";
    private List<String> allMessages = new ArrayList<>();

    public final String getMessageInput()
    {
        return messageInput;
    }

    public final void setMessageInput(String messageInput)
    {
        this.messageInput = messageInput;
    }

    public final List<String> getAllMessages()
    {
        return allMessages;
    }

    public final void setAllMessages(List<String> updatedAllMessages)
    {
        this.allMessages = updatedAllMessages;
    }

    @Override
    public String toString()
    {
        return "ChatState{"
                + "messageInput='" + messageInput + '\''
                + ", allMessages='" + allMessages.toString() + '\''
                + '}';
    }
}
