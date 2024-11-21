package interface_adapter.send_message;

import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

/**
 * Controller for the Send Message Use Case.
 */
public class SendMessageController
{
    // == INSTANCE VARIABLES ==
    private final SendMessageInputBoundary sendMessageUseCaseInteractor;

    // == CONSTRUCTOR ==
    public SendMessageController(SendMessageInputBoundary userSendMessageUseCaseInteractor)
    {
        this.sendMessageUseCaseInteractor = userSendMessageUseCaseInteractor;
    }

    // == CONTROLLER METHODS ==

    /**
     * Executes the "Send Message" Use Case.
     * @param threadID the threadID of the thread the message is being sent within
     * @param content the content of the message
     */
    // TODO: add parameters depending on if we need more info for the input data (senderID?)
    public void execute(String content, Long threadID)
    {
        final SendMessageInputData sendMessageInputData = new SendMessageInputData(content, threadID);
        sendMessageUseCaseInteractor.execute(sendMessageInputData);
    }

    /**
     * Executes the "switch to Threads View" Use Case.
     */
    public void switchToThreadsView()
    {
        this.sendMessageUseCaseInteractor.switchToThreadsView();
    }
}
