package use_case.send_message;

/**
 * Output Data for the Send Message Case.
 */
// TODO: figure out what essential data we need here (do we need 'username'?)
public class SendMessageOutputData
{
    private final Object messageContent;
    private final String username;
    private final boolean useCaseFailed;

    public SendMessageOutputData(String username, Object messageContent, boolean useCaseFailed)
    {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
        this.messageContent = messageContent;
    }

    public final String getUsername()
    {
        return username;
    }

    public final Object getMessageContent()
    {
        return messageContent;
    }

    public final boolean isUseCaseFailed()
    {
        return useCaseFailed;
    }
}
