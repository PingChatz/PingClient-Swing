package entity;

/**
 * The representation of a message in our program.
 */
public abstract class Message {

    Long threadID;
    Long senderID;
    Object content;

    public Message(Object content, Thread thread, User sender) {
        this.content = content;
        this.threadID = thread.getThreadID();
        this.senderID = sender.getUserID();
    }

    public Long getSenderID() {
        return senderID;
    }

    public Long getThreadID() {
        return threadID;
    }

    public Object getContent() {
        return content;
    }
}
