package use_case.get_threads;


import entity.Thread;
import use_case.get_threads.GetThreadsInputBoundary;
import use_case.get_threads.GetThreadsInputData;
import use_case.get_threads.GetThreadsOutputBoundary;
import use_case.get_threads.GetThreadsOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * The get threads Interactor.
 */
public class GetThreadsUseCaseInteractor implements GetThreadsInputBoundary {
    private final GetThreadsThreadDataAccessInterface threadDataAccess;
    private final GetThreadsOutputBoundary presenter;

    // == CONSTRUCTOR ==
    public GetThreadsUseCaseInteractor(GetThreadsThreadDataAccessInterface threadDataAccess,
                                       GetThreadsOutputBoundary presenter) {
        this.threadDataAccess = threadDataAccess;
        this.presenter = presenter;
    }

    // == USE CASE METHODS ==

    // main use case method
    @Override
    public void execute(GetThreadsInputData inputData) {
        try {
            // Use the username from inputData
            String username = inputData.getUsername();

            // Fetch threads for the given username
            List<Thread> threads = threadDataAccess.getThreadsByUsername(username);

            if (threads.isEmpty()) {
                presenter.prepareFailView("No threads found for user: " + username);
            } else {
                presenter.prepareSuccessView(new GetThreadsOutputData(threads));
            }
        } catch (Exception e) {
            presenter.prepareFailView("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public void switchToChatView(Long threadID) {
        presenter.switchToChatView(threadID);
    }

    @Override
    public void switchToAddThreadView()
    {
        presenter.switchToAddThreadView();
    }
}
