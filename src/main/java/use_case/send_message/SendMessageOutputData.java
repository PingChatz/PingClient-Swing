package use_case.send_message;

/**
 * Output Data for the Send Message Case.
 */
public class SendMessageOutputData
{
    private final String messageContent;
    private final String username;
    private final boolean useCaseFailed;

    public SendMessageOutputData(String username, String messageContent, boolean useCaseFailed)
    {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.messageContent = messageContent;
    }

    public final String getUsername()
    {
        return username;
    }

    public final String getMessageContent()
    {
        return messageContent;
    }

    public final boolean isUseCaseFailed()
    {
        return useCaseFailed;
    }

    @Override
    public String toString()
    {
        return "OutputData{"
                + "username='" + username + '\''
                + ", content='" + messageContent + '\''
                + '}';
    }
}
