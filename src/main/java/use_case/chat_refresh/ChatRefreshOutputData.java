package use_case.chat_refresh;

import java.util.List;

/**
 * Output data for the Chat Refresh Use Case.
 */
public class ChatRefreshOutputData
{
    private final List<String[]> messages;

    public ChatRefreshOutputData(List<String[]> messages)
    {
        this.messages = messages;

    }

    public final List<String[]> getMessages()
    {
        return messages;
    }

}
