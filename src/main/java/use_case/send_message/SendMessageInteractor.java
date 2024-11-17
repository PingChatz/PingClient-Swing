package use_case.send_message;

/**
 * The Send Message Interactor.
 */
public class SendMessageInteractor implements SendMessageInputBoundary
{
    // == INSTANCE VARIABLES ==
    private final SendMessageUserDataAccessInterface userDataAccessObject;
    private final SendMessageOutputBoundary sendMessagePresenter;

    // == CONSTRUCTOR ==
    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessObject,
                                 SendMessageOutputBoundary sendMessagePresenter)
    {
        this.sendMessagePresenter = sendMessagePresenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    // == USE CASE METHODS ==
    @Override
    public void execute(SendMessageInputData sendMessageInputData)
    {
        // TODO: the bulk of the use case logic will be in this method.
    }

    @Override
    public void switchToThreadsView()
    {
        sendMessagePresenter.switchToThreadsView();
    }
}
