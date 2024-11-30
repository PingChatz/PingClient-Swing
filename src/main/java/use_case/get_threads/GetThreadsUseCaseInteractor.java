package use_case.get_threads;

import use_case.send_message.SendMessageThreadDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The get threads Interactor.
 */
public class GetThreadsUseCaseInteractor implements GetThreadsInputBoundary {
    private final SendMessageUserDataAccessInterface userDataAccess;
    private final SendMessageThreadDataAccessInterface threadDataAccess;
    private final GetThreadsOutputBoundary presenter;

    // == CONSTRUCTOR ==
    public GetThreadsUseCaseInteractor(SendMessageUserDataAccessInterface userDataAccess,
                                       SendMessageThreadDataAccessInterface threadDataAccess,
                                       GetThreadsOutputBoundary presenter) {
        this.userDataAccess = userDataAccess;
        this.threadDataAccess = threadDataAccess;
        this.presenter = presenter;
    }

    // == USE CASE METHODS ==

    // main use case method

    @Override
    public void execute()
    {
        try {
            Long userID = userDataAccess.getCurrentUserID();
            if (userID == null) {
                presenter.prepareFailView("User not found.");
                return;
            }

            List<Long> threadIDs = new ArrayList<>();
            // List<Long> threadIDs = threadDataAccess.getUserThreadIDs(userID);
            if (threadIDs == null || threadIDs.isEmpty()) {
                presenter.prepareFailView("No threads found.");
                return;
            }

        }
        catch (Exception e){
            presenter.prepareFailView("Error Occured: " + e.getMessage());
        }
    }

    @Override
    public void switchToChatView(Long threadID) {
        presenter.switchToChatView(threadID);
    }
}
