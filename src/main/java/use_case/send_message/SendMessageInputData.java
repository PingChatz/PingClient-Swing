package use_case.send_message;

/**
 * The Input Data for the Send Message Use Case.
 */
public final class SendMessageInputData
{

    private final String content;

    public SendMessageInputData(String content)
    {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
