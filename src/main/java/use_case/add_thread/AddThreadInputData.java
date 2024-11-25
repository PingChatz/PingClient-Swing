package use_case.add_thread;

/**
 * The Input Data for the Add Thread Use Case.
 */
public final class AddThreadInputData
{

    private final String threadName;
    private final String usernameList;

    public AddThreadInputData(String threadName, String usernameList)
    {
        this.threadName = threadName;
        this.usernameList = usernameList;
    }

    public String getUsernameList()
    {
        return usernameList;
    }

    public String getThreadName()
    {
        return threadName;
    }
}
