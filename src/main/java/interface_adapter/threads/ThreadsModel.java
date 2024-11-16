package interface_adapter.threads;

import interface_adapter.ViewModel;

/**
 * This model creates the text that is used in the labels for the threads view.
 */
public class ThreadsModel extends ViewModel<ThreadsState>
{
    public static final String TITLE_LABEL = "Messages";
    public static final String REFRESH_LABEL = "Refresh";
    public static final String LOGOUT_LABEL = "Logout";

    public ThreadsModel() {
        super("ThreadsViewState");
        setState(new ThreadsState());
    }
}
