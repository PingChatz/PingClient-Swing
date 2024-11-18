package interface_adapter.send_message;

import use_case.send_message.SendMessageInputBoundary;

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
     */
    public void execute()
    {
        // TODO: implement this when building the Use Case.
    }

    /**
     * Executes the "switch to Threads View" Use Case.
     */
    public void switchToThreadsView()
    {
        this.sendMessageUseCaseInteractor.switchToThreadsView();
    }
}
