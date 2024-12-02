package interface_adapter.send_message;

import java.awt.*;

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
    public static final Font MESSAGE_FONT = new Font("Arial", Font.PLAIN, 13);
    public static final int FONT_SIZE_MESSAGE_SENDER_USERNAME_LABEL = 10;
    public static final int FONT_SIZE_MESSAGE_CONTENT_LABEL = 13;
    public static final int FONT_SIZE_MESSAGE_TIMESTAMP_LABEL = 10;
    public static final int[] LIGHT_BLUE_RGB = {173, 216, 230};
    public static final int[] LIGHT_GREY_RGB = {211, 211, 211};
    public static final int MESSAGE_SPACING = 2;

    public ChatViewModel()
    {
        super("chat");
        setState(new ChatState());
    }
}
