package use_case.add_thread;

/**
 * Output Data for the Add Thread Use Case.
 */
public class AddThreadOutputData
{

    private final String threadName;

    public AddThreadOutputData(String threadName)
    {
        this.threadName = threadName;
    }

    public final String getThreadName()
    {
        return threadName;
    }
}
