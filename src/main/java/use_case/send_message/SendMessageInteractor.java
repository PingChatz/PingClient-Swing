package use_case.send_message;

import entity.Message;
import entity.MessageFactory;

/**
 * The Send Message Interactor.
 */
public class SendMessageInteractor implements SendMessageInputBoundary
{
    // == CONSTANTS ==
    private static final int MESSAGE_MAX_LENGTH = 280;

    // == INSTANCE VARIABLES ==
    private final SendMessageUserDataAccessInterface userDataAccessObject;
    private final SendMessageMessageDataAccessInterface messageDataAccessObject;
    private final SendMessageThreadDataAccessInterface threadDataAccessObject;

    private final SendMessageOutputBoundary sendMessagePresenter;
    private final MessageFactory messageFactory;

    // == CONSTRUCTOR ==
    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessObject,
                                 SendMessageMessageDataAccessInterface messageDataAccessObject,
                                 SendMessageThreadDataAccessInterface threadDataAccessObject,
                                 MessageFactory messageFactory,
                                 SendMessageOutputBoundary sendMessagePresenter)
    {
        this.sendMessagePresenter = sendMessagePresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.messageDataAccessObject = messageDataAccessObject;
        this.threadDataAccessObject = threadDataAccessObject;
        this.messageFactory = messageFactory;
    }

    // == USE CASE METHODS ==
    @Override
    public void execute(SendMessageInputData sendMessageInputData)
    {
        // Check whether the message content is empty or too long.
        if (sendMessageInputData.getContent().isEmpty())
        {
            sendMessagePresenter.prepareFailView("Message field is empty.");
        }
        else if (sendMessageInputData.getContent().length() >= MESSAGE_MAX_LENGTH)
        {
            sendMessagePresenter.prepareFailView(
                    "Message is too long. Must be under " + MESSAGE_MAX_LENGTH + " characters.");
        }
        // Otherwise, create Message object and save it to the database
        else
        {
            // Create the message object to save
            final Long senderID = userDataAccessObject.getCurrentUserID();
            final String senderName = userDataAccessObject.getCurrentUsername();
            final Message message = messageFactory.create(sendMessageInputData.getContent(),
                                                          sendMessageInputData.getThreadID(),
                                                          senderID, senderName);

            // Save the message object, raise exception is anything server-side fails.
            // TODO: add try-except block here to handle any exceptions raised by the server
            messageDataAccessObject.save(message);

            //  Prepare output data object and probe presenter.
            final SendMessageOutputData sendMessageOutputData =
                    new SendMessageOutputData(message.getSenderUsername(), message.getContent(), false);
            sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
        }
    }

    @Override
    public void switchToThreadsView()
    {
        sendMessagePresenter.switchToThreadsView();
    }
}
