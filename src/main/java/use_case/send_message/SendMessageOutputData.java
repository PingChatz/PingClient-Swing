package use_case.send_message;

/**
 * Output Data for the Send Message Case.
 */
public class SendMessageOutputData
{
    private final String content;
    private final String senderUsername;
    private final boolean useCaseFailed;
    private final String timestamp;

    public SendMessageOutputData(String senderUsername, String content, String timestamp, boolean useCaseFailed)
    {
        this.senderUsername = senderUsername;
        this.useCaseFailed = useCaseFailed;
        this.content = content;
        this.timestamp = timestamp;
    }

    public final String getSenderUsername()
    {
        return senderUsername;
    }

    public final String getContent()
    {
        return content;
    }

    public final String getTimestamp()
    {
        return timestamp;
    }

    public final boolean isUseCaseFailed()
    {
        return useCaseFailed;
    }

    @Override
    public String toString()
    {
        return "OutputData{"
                + "username='" + senderUsername + '\''
                + ", content='" + content + '\''
                + ", timestamp='" + content + '\''
                + '}';
    }
}
