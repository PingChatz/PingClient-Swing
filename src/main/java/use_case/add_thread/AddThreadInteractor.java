package use_case.add_thread;

import entity.Thread;
import entity.ThreadFactory;

/**
 * The Add Thread Interactor.
 */
public class AddThreadInteractor implements AddThreadInputBoundary
{
    private final AddThreadThreadDataAccessInterface threadDataAccessObject;
    private final ThreadFactory threadFactory;
    private final AddThreadOutputBoundary addThreadPresenter;

    public AddThreadInteractor(AddThreadThreadDataAccessInterface threadDataAccessObject,
                               AddThreadOutputBoundary addThreadPresenter,
                               ThreadFactory threadFactory)
    {
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
        } else if (addThreadInputData.getThreadName().length() > Thread.THREAD_NAME_MAX_LENGTH)
        {
            addThreadPresenter.prepareFailView(
                    "Thread name is too long. Must be under "
                            + Thread.THREAD_NAME_MAX_LENGTH + " characters.");
        } else if (addThreadInputData.getThreadName().length() < Thread.THREAD_NAME_MIN_LENGTH)
        {
            addThreadPresenter.prepareFailView(
                    "Thread name is too short. Must be at least "
                            + Thread.THREAD_NAME_MIN_LENGTH + " characters.");
        }

        // TODO: check for formatting for Thread Name?

        // Check whether the users list is empty or poorly formatted
        else if (addThreadInputData.getUsernameList().isEmpty())
        {
            addThreadPresenter.prepareFailView("List of Users is Empty");
        } else if (!userListIsWellFormatted(addThreadInputData.getUsernameList()))
        {
            addThreadPresenter.prepareFailView("List of users is poorly formatted. \n "
                    + "(should be separated by commas and contain no spaces)");
        } else
        {
            // complete the username list with the current user's username.
            String userList = createFormattedUserList(addThreadInputData.getUsernameList());

            // create thread object to save
            Thread threadToSave = threadFactory.create(addThreadInputData.getThreadName(), userList);

            // try to save the new thread to the server
            try
            {
                Thread threadToPresent = threadDataAccessObject.save(threadToSave);

                // create output data and fire presenter
                AddThreadOutputData outputData = new AddThreadOutputData(
                        threadToPresent.getName(),
                        threadToPresent.getThreadID());
                addThreadPresenter.prepareSuccessView(outputData,
                        "New thread '" + threadToPresent.getName() + "' has been successfully created.");
            } catch (Exception exception)
            {
                addThreadPresenter.prepareFailView("Server Error");
            }
        }
    }

    /**
     * Returns true if the given string is well-formatted, according to the regex.
     *
     * @param string string to check
     * @return true if usernameList is well-formatted, false otherwise
     */
    private boolean userListIsWellFormatted(String string)
    {
        if (string == null || string.isEmpty())
        {
            return false;
        }
        String regex = "^ *[a-zA-Z0-9]+( *, *[a-zA-Z0-9]+)* *$";
        return string.matches(regex);
    }

    /**
     * Returns the full list of users in the thread, as a comma-separated String.
     *
     * @param userList the list of users from the input data
     * @return a concatenation of the two that follows the regex
     */
    private String createFormattedUserList(String userList)
    {
        return userList.replaceAll("\\s+", "");
    }

    @Override
    public void switchToThreadsView()
    {
        addThreadPresenter.switchToThreadsView();
    }
}
