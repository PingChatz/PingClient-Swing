package use_case.chat_refresh;

public class ChatRefreshInputData
{
    private final Long threadID;

    public ChatRefreshInputData(Long threadID)
    {
        this.threadID = threadID;
    }

    public Long getThreadID()
    {
        return threadID;
    }
}
