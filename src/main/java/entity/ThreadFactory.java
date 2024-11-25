package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating threads.
 */
public class ThreadFactory
{
    /**
     * This method creates a new thread object.
     * @param name the name of the new thread
     * @return a new Thread object
     */
    // TODO: change the body when Thread Entity changes
    public Thread createNew(String name)
    {
        // /////
        User newUser = new User("", "", "");
        List<User> mockList = new ArrayList<>();
        mockList.add(newUser);
        // /////
        return new Thread(name, mockList);
    }

    /**
     * This method creates a thread object that already exists in the database.
     * @param name the name of the new thread
     * @param usernameList comma separated string of usernames
     * @return a Thread object
     */
    // TODO: change the body when Thread Entity changes
    public Thread create(String name, String usernameList)
    {
        // //////
        User newUser = new User("", "", "");
        List<User> mockList = new ArrayList<>();
        mockList.add(newUser);
        // /////
        return new Thread(name, mockList);
    }

}
