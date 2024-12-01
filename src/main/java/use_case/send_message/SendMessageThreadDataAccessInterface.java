package use_case.send_message;

import entity.Message;

import java.util.List;

/**
 * DAO for the Send Message Use Case ; for the Thread.
 * Contains all necessary methods to gather / change the data from / for the Thread that the message is being sent on.
 */
// TODO: figure out what data we need for the entire send message use case from the Thread.
public interface SendMessageThreadDataAccessInterface
{
    /**
     * Returns the name of the current thread of the application.
     *
     * @return the name of this thread ; null indicated that no thread has yet been accessed.
     */
    String getCurrentThreadName();

    /**
     * Returns the threadID of the current thread of the application.
     *
     * @return the threadID of this thread.
     */
    String getCurrentThreadID();

    /**
     * Returns a list of all the messages in the current thread.
     *
     * @return a list messages in the thread.
     */
    // TODO: figure out if I should be returning a list of: Message, MessageID, Content, etc... I'm thinking
    //  list of MessageIDs, but IDK how we would access them past that.
    List<Message> getMessageList();

    /**
     * Update the list of messages with the sent message.
     *
     * @param message the message that was sent.
     */
    // TODO: determine whether Message is the right object type for this method.
    void updateMessageList(Message message);
}
