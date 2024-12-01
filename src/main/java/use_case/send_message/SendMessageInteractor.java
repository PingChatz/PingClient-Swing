package use_case.send_message;

import entity.Message;
import entity.MessageFactory;

/**
 * The Send Message Interactor.
 */
public class SendMessageInteractor implements SendMessageInputBoundary
{

    private final SendMessageMessageDataAccessInterface messageDataAccessObject;

    private final SendMessageOutputBoundary sendMessagePresenter;
    private final MessageFactory messageFactory;

    public SendMessageInteractor(SendMessageMessageDataAccessInterface messageDataAccessObject,
                                 MessageFactory messageFactory,
                                 SendMessageOutputBoundary sendMessagePresenter)
    {
        this.sendMessagePresenter = sendMessagePresenter;
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
            // create the message to save
            final Message messageToSave = messageFactory.create(
                    sendMessageInputData.getContent(),
                    sendMessageInputData.getSenderUsername());

            try
            {
                // Save the message object, raise exception is anything server-side fails.
                final Message messageToPresent = messageDataAccessObject.save(messageToSave,
                        sendMessageInputData.getThreadID());

                // Prepare output data object and call the presenter.
                final SendMessageOutputData sendMessageOutputData =
                        new SendMessageOutputData(messageToPresent.getSenderUsername(),
                                messageToPresent.getContent(),
                                messageToPresent.getTimestamp(), false);

                sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
            }
            catch (Exception exception)
            {
                sendMessagePresenter.prepareFailView("Server Error");
            }
        }
    }

    @Override
    public void switchToThreadsView()
    {
        sendMessagePresenter.switchToThreadsView();
    }
}
