package use_case.send_message;

/**
 * The Input Data for the Send Message Use Case.
 */
public final class SendMessageInputData
{

    private final Long threadID;
    private final String content;

    public SendMessageInputData(String content, Long threadID)
    {
        this.threadID = threadID;
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public Long getThreadID()
    {
        return threadID;
    }
}
