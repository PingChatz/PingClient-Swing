package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
// TODO: overwrite with our code
public class SignupOutputData
{

    private final String username;
    private final boolean useCaseFailed;

    public SignupOutputData(String username, boolean useCaseFailed)
    {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public final String getUsername()
    {
        return username;
    }

    public final boolean isUseCaseFailed()
    {
        return useCaseFailed;
    }
}
