package interface_adapter.add_thread;

/**
 * The current state of the Add Thread View Model.
 */
public final class AddThreadState
{
    private String currentUsername = "";
    private String threadName = "";
    private String usernameList = "";
    private String addThreadError;
    private String addThreadSuccess;

    public String getCurrentUsername()
    {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername)
    {
        this.currentUsername = currentUsername;
    }

    public String getThreadName()
    {
        return threadName;
    }

    public String getAddThreadError()
    {
        return addThreadError;
    }

    public String getUsernameList()
    {
        return usernameList;
    }

    public String getAddThreadSuccess()
    {
        return addThreadSuccess;
    }

    public void setAddThreadSuccess(String addThreadSuccess)
    {
        this.addThreadSuccess = addThreadSuccess;
    }

    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }

    public void setAddThreadError(String addThreadError)
    {
        this.addThreadError = addThreadError;
    }

    public void setUsernameList(String usernameList)
    {
        this.usernameList = usernameList;
    }

    @Override
    public String toString()
    {
        return "AddThreadState{"
                + "thread_name='" + threadName + '\''
                + ", users_list='" + usernameList + '\''
                + ", error='" + addThreadError + '\''
                + '}';
    }
}
