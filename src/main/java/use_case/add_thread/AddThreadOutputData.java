package use_case.add_thread;

/**
 * Output Data for the Add Thread Use Case.
 */
public class AddThreadOutputData
{

    private final String threadName;
    private final Long threadID;

    public AddThreadOutputData(String threadName, Long threadID)
    {
        this.threadName = threadName;
        this.threadID = threadID;
    }

    public final String getThreadName()
    {
        return threadName;
    }

    public final Long getThreadID()
    {
        return threadID;
    }
}
