package use_case.add_thread;

import entity.ThreadFactory;
import entity.Thread;

/**
 * The Add Thread Interactor.
 */
public class AddThreadInteractor implements AddThreadInputBoundary
{
    private static final int THREAD_NAME_MAX_LENGTH = 100;
    private static final int THREAD_NAME_MIN_LENGTH = 3;
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
        // Check whether the thread name is empty, too long or too short
        if (addThreadInputData.getThreadName().isEmpty())
        {
            addThreadPresenter.prepareFailView("Thread name field is empty.");
        }
        else if (addThreadInputData.getThreadName().length() > THREAD_NAME_MAX_LENGTH)
        {
            addThreadPresenter.prepareFailView(
                    "Thread name is too long. Must be under " + THREAD_NAME_MAX_LENGTH + " characters.");
        }
        else if (addThreadInputData.getThreadName().length() <= THREAD_NAME_MIN_LENGTH)
        {
            addThreadPresenter.prepareFailView(
                    "Thread name is too short. Must be at least "
                            + THREAD_NAME_MIN_LENGTH + " characters.");
        }

        // Check whether the users list is empty or poorly formatted
        else if (addThreadInputData.getUsernameList().isEmpty())
        {
            addThreadPresenter.prepareFailView("List of Users is Empty");
        }
        // TODO: check with team that usernames can't have spaces in them
        else if (!isCommaSeparatedNoSpaces(addThreadInputData.getUsernameList()))
        {
            addThreadPresenter.prepareFailView("List of users is poorly formatted. \n "
                    + "(should be separated by commas and contain no spaces)");
        }
        else
        {
            Thread threadToSave = threadFactory.create(addThreadInputData.getThreadName(),
                    addThreadInputData.getUsernameList());
            
            // TODO: code the save() function and add try-except block to catch server exceptions
            Thread threadToPresent = threadDataAccessObject.save(threadToSave);

            AddThreadOutputData outputData = new AddThreadOutputData(
                    threadToPresent.getName(),
                    threadToPresent.getThreadID());
            addThreadPresenter.prepareSuccessView(outputData,
                    "New thread '" + threadToPresent.getName() + "' has been successfully created.");
        }
    }

    /**
     * Returns true if the given string is comma separated.
     * @param string string to check
     * @return true if usernameList is comma separated, false otherwise
     */
    private boolean isCommaSeparatedNoSpaces(String string)
    {
        if (string == null || string.isEmpty())
        {
            return false;
        }
        // TODO: change regex to be more selecting when more important things are done
        String regex = "([^, ]+,)*[^, ]+";
        return string.matches(regex);
    }

    @Override
    public void switchToThreadsView()
    {
        addThreadPresenter.switchToThreadsView();
    }
}
