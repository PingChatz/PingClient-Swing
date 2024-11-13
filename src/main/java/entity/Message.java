package entity;

/**
 * The representation of a message in our program.
 * ToDo: Check class to abstract later
 */
public class Message {

    private Long threadID;
    private Long senderID;
    private Object content;

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
