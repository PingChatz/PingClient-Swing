package use_case.get_threads;

import use_case.send_message.SendMessageMessageDataAccessInterface;
import use_case.send_message.SendMessageThreadDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;

/**
 * The get threads Interactor.
 */
public class GetThreadsUseCaseInteractor implements GetThreadsInputBoundary
{
    // == INSTANCE VARIABLES ==
    // TODO change these from SendMEssage interfaces to getThreads Interfaces( create new interfaces)
    private final SendMessageUserDataAccessInterface userDataAccessObject;
    private final SendMessageMessageDataAccessInterface messageDataAccessObject;
    private final SendMessageThreadDataAccessInterface threadDataAccessObject;
    private final GetThreadsOutputBoundary getThreadsPresenter;

    // == CONSTRUCTOR ==
    public GetThreadsUseCaseInteractor(SendMessageUserDataAccessInterface userDataAccessObject,
                                       SendMessageMessageDataAccessInterface messageDataAccessObject,
                                       SendMessageThreadDataAccessInterface threadDataAccessObject,
                                       GetThreadsOutputBoundary getThreadsPresenter)
    {
        this.getThreadsPresenter = getThreadsPresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.messageDataAccessObject = messageDataAccessObject;
        this.threadDataAccessObject = threadDataAccessObject;
    }

    // == USE CASE METHODS ==
    @Override
    public void execute(GetThreadsInputBoundary getThreadsInputData)
    {
        // TODO: the bulk of the use case logic will be in this method.
    }

    // TODO: will probably need to split execute() into private helper functions.

    @Override
    public void switchToThreadsView()
    {
        getThreadsPresenter.switchToThreadsView();
    }

    @Override
    public void execute()
    {
        // TODO main use case for getting threads
    }


    @Override
    public void switchToChatView()
    {
        this.getThreadsPresenter.switchToChatView();
    }
}
