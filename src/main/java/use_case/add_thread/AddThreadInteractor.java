package use_case.add_thread;

import entity.Thread;
import entity.ThreadFactory;

/**
 * The Add Thread Interactor.
 */
public class AddThreadInteractor implements AddThreadInputBoundary
{
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
        else if (addThreadInputData.getThreadName().length() > Thread.THREAD_NAME_MAX_LENGTH)
        {
            addThreadPresenter.prepareFailView(
                    "Thread name is too long. Must be under "
                            + Thread.THREAD_NAME_MAX_LENGTH + " characters.");
        }
        else if (addThreadInputData.getThreadName().length() <= Thread.THREAD_NAME_MIN_LENGTH)
        {
            addThreadPresenter.prepareFailView(
                    "Thread name is too short. Must be at least "
                            + Thread.THREAD_NAME_MIN_LENGTH + " characters.");
        }

        // Check whether the users list is empty or poorly formatted
        else if (addThreadInputData.getUsernameList().isEmpty())
        {
            addThreadPresenter.prepareFailView("List of Users is Empty");
        }
        else if (!isCommaSeparatedNoSpaces(addThreadInputData.getUsernameList()))
        {
            addThreadPresenter.prepareFailView("List of users is poorly formatted. \n "
                    + "(should be separated by commas and contain no spaces)");
        }
        else
        {
            // complete the username list with the current user's username.
            // TODO: code the getCurrentUsername() function and add try-except block to catch server exceptions
            final String fullUserList = createFullUserList(
                    addThreadInputData.getUsernameList(),
                    userDataAccessObject.getCurrentUsername());

            // create thread object and save it to the server
            Thread threadToSave = threadFactory.create(addThreadInputData.getThreadName(), fullUserList);
            // TODO: code the save() function and add try-except block to catch server exceptions
            Thread threadToPresent = threadDataAccessObject.save(threadToSave);

            // create output data and fire presenter
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

    /**
     * Returns the full list of users in the thread.
     * @param userList the list of users from the input data
     * @param currentUsername the username of the current user
     * @return a concatenation of the two that follows the regex
     */
    private String createFullUserList(String userList, String currentUsername)
    {
        return userList + "," + currentUsername;
    }

    @Override
    public void switchToThreadsView()
    {
        addThreadPresenter.switchToThreadsView();
    }
}
