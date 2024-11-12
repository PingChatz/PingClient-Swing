package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The representation of a thread in our program.
 */
public class Thread {

    private final Long threadID = null;
    private String name;
    private final List<User> userList;
    private final List<Message> messageList;
    // TODO: remove 'null' when below TODOs are completed

    // 2 constructors, the first one is just a default Thread (no users)
    public Thread(String name) {
        this.name = name;
        this.userList = new ArrayList<>();
        this.messageList = new ArrayList<>();
        // TODO: SpringBoot save a randomized threadID
        //  (maybe use a private helper method below to generate the ID to avoid repeating the code in both constructors)
    }

    public Thread(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
        this.messageList = new ArrayList<>();
        // TODO: SpringBoot save a randomized threadID
        //  (maybe use a private helper method below to generate the ID to avoid repeating the code in both constructors)
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public Long getThreadID() {
        return this.threadID;
    }

    /**
     * Adds a new User to the Thread.
     * @param newUser is the new User.
     */
    public void addUser(User newUser) {
        this.userList.add(newUser);
    }

    /**
     * Removes a user from the Thread. Do nothing if the user is not in the Thread.
     * @param userToRemove is that user.
     */
    public void removeUser(User userToRemove) {
        this.userList.remove(userToRemove);
    }
}

