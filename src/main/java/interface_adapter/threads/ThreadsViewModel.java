package interface_adapter.threads;

import interface_adapter.ViewModel;

/**
 * This model creates the text that is used in the labels for the threads view.
 */
public class ThreadsViewModel extends ViewModel<ThreadsState>
{
    public static final String TITLE_LABEL = "Messages";
    public static final String REFRESH_LABEL = "Refresh";
    public static final String LOGOUT_LABEL = "Logout";

    public static final int TITLE_FONT_SIZE = 24;

    // initialize sizes for JObjects
    public static final int TOP_PANEL_LAYOUT_ROWS = 1;
    public static final int TOP_PANEL_LAYOUT_COLUMNS = 2;
    public static final int TOP_BORDER_SPACING = 10;
    public static final int BORDER_SPACING = 40;
    public static final int THREADSLIST_WIDTH = 400;
    public static final int THREADSLIST_HEIGHT = 300;

    public ThreadsViewModel()
    {
        super("threads");
        setState(new ThreadsState());
    }
}
