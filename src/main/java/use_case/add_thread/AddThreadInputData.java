package use_case.add_thread;

/**
 * The Input Data for the Add Thread Use Case.
 */
public final class AddThreadInputData
{

    private final String threadName;
    private final String usersList;

    public AddThreadInputData(String threadName, String userslist)
    {
        this.threadName = threadName;
        this.usersList = userslist;
    }

    public String getUsersList()
    {
        return usersList;
    }

    public String getThreadName()
    {
        return threadName;
    }
}
