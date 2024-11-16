package interface_adapter.send_message;

import use_case.signup.SignupInputBoundary;

/**
 * Controller for the Send Message Use Case.
 */
public class SendMessageController
{
    // == INSTANCE VARIABLES ==
    // TODO: change "SignupInputBoundary" to "SendMessageInputBoundary" when that Class has been created.
    private final SignupInputBoundary userSendMessageUseCaseInteractor;

    // == CONSTRUCTOR ==
    public SendMessageController(SignupInputBoundary userSendMessageUseCaseInteractor)
    {
        this.userSendMessageUseCaseInteractor = userSendMessageUseCaseInteractor;
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
     * Executes the "switch to Thread View" Use Case.
     */
    public void switchToThreadView()
    {
        // TODO: change ".switchToLoginView()" to ".switchToThreadView()" when class is ready.
        userSendMessageUseCaseInteractor.switchToLoginView();
    }
}
