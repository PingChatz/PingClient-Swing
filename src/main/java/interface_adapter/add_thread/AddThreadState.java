package interface_adapter.add_thread;

/**
 * The current state of the Add Thread View Model.
 */
public final class AddThreadState
{
    private String threadName = "";
    private String usersList = "";
    private String addThreadError;

    public String getThreadName()
    {
        return threadName;
    }

    public String getAddThreadError()
    {
        return addThreadError;
    }

    public String getUsersList()
    {
        return usersList;
    }

    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }

    public void setAddThreadError(String addThreadError)
    {
        this.addThreadError = addThreadError;
    }

    public void setUsersList(String usersList)
    {
        this.usersList = usersList;
    }

    @Override
    public String toString()
    {
        return "AddThreadState{"
                + "thread_name='" + threadName + '\''
                + ", users_list='" + usersList + '\''
                + '}';
    }
}
