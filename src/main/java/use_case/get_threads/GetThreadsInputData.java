package use_case.get_threads;

/**
 * Input data for the Get Threads Use Case.
 */
public class GetThreadsInputData
{
    private final String username;

    public GetThreadsInputData(String username)
    {
        this.username = username;
    }

    public final String getUsername()
    {
        return username;
    }
}
