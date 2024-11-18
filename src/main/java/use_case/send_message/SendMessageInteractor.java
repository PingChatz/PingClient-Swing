package use_case.send_message;

/**
 * The Send Message Interactor.
 */
public class SendMessageInteractor implements SendMessageInputBoundary
{
    // == INSTANCE VARIABLES ==
    private final SendMessageUserDataAccessInterface userDataAccessObject;
    private final SendMessageMessageDataAccessInterface messageDataAccessObject;
    private final SendMessageThreadDataAccessInterface threadDataAccessObject;
    private final SendMessageOutputBoundary sendMessagePresenter;

    // == CONSTRUCTOR ==
    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessObject,
                                 SendMessageMessageDataAccessInterface messageDataAccessObject,
                                 SendMessageThreadDataAccessInterface threadDataAccessObject,
                                 SendMessageOutputBoundary sendMessagePresenter)
    {
        this.sendMessagePresenter = sendMessagePresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.messageDataAccessObject = messageDataAccessObject;
        this.threadDataAccessObject = threadDataAccessObject;
    }

    // == USE CASE METHODS ==
    @Override
    public void execute(SendMessageInputData sendMessageInputData)
    {
        // TODO: the bulk of the use case logic will be in this method.
    }

    // TODO: will probably need to split execute() into private helper functions.

    @Override
    public void switchToThreadsView()
    {
        sendMessagePresenter.switchToThreadsView();
    }
}
