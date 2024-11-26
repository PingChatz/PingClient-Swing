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

    private final SendMessageOutputBoundary sendMessagePresenter;
    private final MessageFactory messageFactory;

    // == CONSTRUCTOR ==
    public SendMessageInteractor(SendMessageUserDataAccessInterface userDataAccessObject,
                                 SendMessageMessageDataAccessInterface messageDataAccessObject,
                                 MessageFactory messageFactory,
                                 SendMessageOutputBoundary sendMessagePresenter)
    {
        this.sendMessagePresenter = sendMessagePresenter;
        this.userDataAccessObject = userDataAccessObject;
        this.messageDataAccessObject = messageDataAccessObject;
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
            // TODO: add try-except block here to handle server error
            final String senderName = userDataAccessObject.getCurrentUsername();
            final Message messageToSave = messageFactory.create(sendMessageInputData.getContent(), senderName);

            // Save the message object, raise exception is anything server-side fails.
            // TODO: add try-except block here to handle any exceptions raised by the server
            final Message messageToPresent = messageDataAccessObject.save(messageToSave,
                    sendMessageInputData.getThreadID());

            // Prepare output data object and probe presenter.
            final SendMessageOutputData sendMessageOutputData =
                    new SendMessageOutputData(messageToPresent.getSenderUsername(),
                            messageToPresent.getContent(),
                            messageToPresent.getTimestamp(), false);

            sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
        }
    }

    @Override
    public void switchToThreadsView()
    {
        sendMessagePresenter.switchToThreadsView();
    }
}
