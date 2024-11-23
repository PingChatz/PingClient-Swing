package interface_adapter.add_thread;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
// TODO: overwrite with our code
public class AddThreadViewModel extends ViewModel<AddThreadState>
{

    public static final String TITLE_LABEL = "Create New Thread View";
    public static final String THREAD_NAME_LABEL = "Enter new thread name";
    public static final String USERS_LIST_LABEL = "Enter list of users of this thread";
    public static final String TO_THREADS_BUTTON_LABEL = "Back to all threads";
    public static final String ADD_THREAD_BUTTON_LABEL = "Create thread";

    public AddThreadViewModel()
    {
        super("add thread");
        setState(new AddThreadState());
    }
}
