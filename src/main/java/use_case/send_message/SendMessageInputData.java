package use_case.send_message;

/**
 * The Input Data for the Send Message Use Case.
 */
public final class SendMessageInputData
{

    private final Long threadID;
    private final String content;
    private final String senderUsername;

    public SendMessageInputData(String content, Long threadID, String senderUsername)
    {
        this.threadID = threadID;
        this.content = content;
        this.senderUsername = senderUsername;
    }

    public String getContent()
    {
        return content;
    }

    public Long getThreadID()
    {
        return threadID;
    }

    public String getSenderUsername()
    {
        return senderUsername;
    }
}
