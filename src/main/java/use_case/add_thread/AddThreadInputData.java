package use_case.add_thread;

/**
 * The Input Data for the Add Thread Use Case.
 */
public final class AddThreadInputData
{

    private final String threadName;
    private final String usernameList;
    private final String currentUsername;

    public AddThreadInputData(String threadName, String usernameList, String currentUsername)
    {
        this.threadName = threadName;
        this.usernameList = usernameList;
        this.currentUsername = currentUsername;
    }

    public String getUsernameList()
    {
        return usernameList;
    }

    public String getThreadName()
    {
        return threadName;
    }

    public String getCurrentUsername()
    {
        return currentUsername;
    }
}
