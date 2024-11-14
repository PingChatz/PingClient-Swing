package interface_adapter.send_message;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class ChatViewModel extends ViewModel<ChatState>
{
    // TODO: change the name of the title so that it depends on the name of the Thread
    public static final String TITLE_LABEL = "[name of Thread]";

    public static final String SEND_BUTTON_LABEL = "Send";
    public static final String RETURN_BUTTON_LABEL = "Return to Threads";

    public ChatViewModel()
    {
        super("send message");
    }

}
