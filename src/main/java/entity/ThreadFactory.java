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
     *
     * @param name               the name of the new thread
     * @param usernameListString comma separated string of usernames
     * @return a new Thread object
     */
    public Thread create(String name, String usernameListString)
    {
        List<String> usernameList = convertCommaSeparatedToList(usernameListString);
        return new Thread(name, usernameList);
    }

    private List<String> convertCommaSeparatedToList(String usernameListString)
    {
        final List<String> result = new ArrayList<>();

        if (usernameListString != null && !usernameListString.isEmpty())
        {
            String[] parts = usernameListString.split(",");
            for (String part : parts)
            {
                result.add(part.trim());
            }
        }
        return result;
    }

}
