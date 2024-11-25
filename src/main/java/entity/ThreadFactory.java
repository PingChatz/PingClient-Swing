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
     * @param usernameList comma separated string of usernames
     * @return a new Thread object
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
