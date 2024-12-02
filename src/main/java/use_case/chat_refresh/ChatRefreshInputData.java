package use_case.chat_refresh;

/**
 * The Input Data for the Chat Refresh Use Case.
 */
public class ChatRefreshInputData
{
    private final Long threadID;

    public ChatRefreshInputData(Long threadID)
    {
        this.threadID = threadID;
    }

    public final Long getThreadID()
    {
        return threadID;
    }
}
