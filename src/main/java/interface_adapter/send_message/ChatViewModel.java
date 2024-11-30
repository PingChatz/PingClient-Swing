package interface_adapter.send_message;

import interface_adapter.ViewModel;

/**
 * The View Model for the Chat View.
 */
public class ChatViewModel extends ViewModel<ChatState>
{
    public static final String SEND_BUTTON_LABEL = "Send";
    public static final String RETURN_BUTTON_LABEL = "Return To Threads";
    public static final String REFRESH_BUTTON_LABEL = "Refresh";

    public static final int BORDER_DIMENSIONS = 40;
    // TODO: figure out the constants for what a message should look like

    public ChatViewModel()
    {
        super("chat");
        setState(new ChatState());
    }
}
