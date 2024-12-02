package use_case.get_threads;

import java.util.List;

import entity.Thread;

/**
 * The get threads Interactor.
 */
public class GetThreadsUseCaseInteractor implements GetThreadsInputBoundary
{
    private final GetThreadsThreadDataAccessInterface threadDataAccess;
    private final GetThreadsOutputBoundary presenter;

    public GetThreadsUseCaseInteractor(GetThreadsThreadDataAccessInterface threadDataAccess,
                                       GetThreadsOutputBoundary presenter)
    {
        this.threadDataAccess = threadDataAccess;
        this.presenter = presenter;
    }

    @Override
    public void execute(GetThreadsInputData inputData)
    {
        try
        {
            // Use the username from inputData
            String username = inputData.getUsername();

            // Fetch threads for the given username
            List<Thread> threads = threadDataAccess.getThreadsByUsername(username);

            if (threads.isEmpty())
            {
                presenter.prepareFailView("No threads found for user: " + username);
            }
            else
            {
                presenter.prepareSuccessView(new GetThreadsOutputData(threads));
            }
        }
        catch (Exception exception)
        {
            presenter.prepareFailView("Error occurred: " + exception.getMessage());
        }
    }

    @Override
    public void switchToChatView(Long threadID)
    {
        presenter.switchToChatView(threadID);
    }

    @Override
    public void switchToAddThreadView()
    {
        presenter.switchToAddThreadView();
    }
}
