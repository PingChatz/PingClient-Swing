package use_case.add_thread;

import entity.ThreadFactory;

/**
 * The Add Thread Interactor.
 */
public class AddThreadInteractor implements AddThreadInputBoundary
{
    private static final int THREAD_NAME_MAX_LENGTH = 100;
    // TODO: create the 2 necessary DAIs
    // TODO: import the DAO for threads and users (message DAO not needed here)
    private final AddThreadUserDataAccessInterface userDataAccessObject;
    private final AddThreadThreadDataAccessInterface threadDataAccessObject;
    private final ThreadFactory threadFactory;
    private final AddThreadOutputBoundary addThreadPresenter;

    public AddThreadInteractor(AddThreadUserDataAccessInterface userDataAccessObject,
                               AddThreadThreadDataAccessInterface threadDataAccessObject,
                               AddThreadOutputBoundary addThreadPresenter,
                               ThreadFactory threadFactory)
    {
        this.userDataAccessObject = userDataAccessObject;
        this.threadDataAccessObject = threadDataAccessObject;
        this.addThreadPresenter = addThreadPresenter;
        this.threadFactory = threadFactory;
    }

    @Override
    public void execute(AddThreadInputData addThreadInputData)
    {
        // Check whether the thread name is empty or too long
        if (addThreadInputData.getThreadName().isEmpty())
        {
            addThreadPresenter.prepareFailView("Thread name field is empty.");
        }
        else if (addThreadInputData.getThreadName().length() >= THREAD_NAME_MAX_LENGTH)
        {
            addThreadPresenter.prepareFailView(
                    "Thread name is too long. Must be under " + THREAD_NAME_MAX_LENGTH + " characters.");
        }

        // Check whether the users list is empty or poorly formatted
        else if (addThreadInputData.getUsernameList().isEmpty())
        {
            addThreadPresenter.prepareFailView("List of Users is Empty");
        }
        // TODO: check with team that usernames can't have spaces in them
        else if (!isCommaSeparated(addThreadInputData.getUsernameList()))
        {
            addThreadPresenter.prepareFailView("List of users is poorly formatted. \n "
                    + "(should be separated by commas and no spaces)");
        }

    }

    /**
     * Returns true if the given string is comma separated.
     * @param usernameList string to check
     * @return true if usernameList is comma separated, false otherwise
     */
    private boolean isCommaSeparated(String usernameList)
    {
        // TODO: write this
        return false;
    }

    @Override
    public void switchToThreadsView()
    {
        addThreadPresenter.switchToThreadsView();
    }
}
