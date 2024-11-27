package use_case.send_message;

import entity.Message;
import entity.MessageFactory;

/**
 * The Send Message Interactor.
 */
public class SendMessageInteractor implements SendMessageInputBoundary
{

    private final SendMessageUserDataAccessInterface userDataAccessObject;
    private final SendMessageMessageDataAccessInterface messageDataAccessObject;

    private final SendMessageOutputBoundary sendMessagePresenter;
    private final MessageFactory messageFactory;

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

    @Override
    public void execute(SendMessageInputData sendMessageInputData)
    {
        // Check whether the message content is empty or too long.
        if (sendMessageInputData.getContent().isEmpty())
        {
            sendMessagePresenter.prepareFailView("Message field is empty.");
        }
        else if (sendMessageInputData.getContent().length() >= Message.MESSAGE_MAX_LENGTH)
        {
            sendMessagePresenter.prepareFailView(
                    "Message is too long. Must be under " + Message.MESSAGE_MAX_LENGTH + " characters.");
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
            // TODO: replace timestamp parameter with formatted timestamp

            sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
        }
    }

    // TODO: determine if you want to move this formatting logic to the message DAO
    /**
     * Returns a human-readable timestamp.
     * @param rawTimestamp the String outputted by the server
     * @return a human-readable timestamp in string format
     */
    private String convertTimestamp(String rawTimestamp)
    {
        // TODO: code this
        return "not implemented";
    }

    @Override
    public void switchToThreadsView()
    {
        sendMessagePresenter.switchToThreadsView();
    }
}
