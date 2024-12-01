package view.custom_panels;

import java.awt.*;

import javax.swing.*;

import interface_adapter.send_message.ChatViewModel;

/**
 * A panel representing a single message in the ChatView.
 */
public class MessagePanel extends JPanel
{

    public MessagePanel(String string1, String string2, String string3, boolean coloured)
    {
        // Set panel layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create labels and set their font sizes
        JLabel label1 = new JLabel(string1);
        label1.setFont(new Font(
                ChatViewModel.MESSAGE_FONT.getName(),
                Font.BOLD,
                ChatViewModel.FONT_SIZE_MESSAGE_SENDER_USERNAME_LABEL));

        JLabel label2 = new JLabel(string2);
        label2.setFont(new Font(ChatViewModel.MESSAGE_FONT.getName(),
                ChatViewModel.MESSAGE_FONT.getStyle(),
                ChatViewModel.FONT_SIZE_MESSAGE_CONTENT_LABEL));

        JLabel label3 = new JLabel(string3);
        label3.setFont(new Font(ChatViewModel.MESSAGE_FONT.getName(),
                Font.ITALIC,
                ChatViewModel.FONT_SIZE_MESSAGE_TIMESTAMP_LABEL));

        // Add labels to the panel
        this.add(label1);
        this.add(label2);
        this.add(label3);

        // Set panel color based on 'coloured' parameter
        if (coloured)
        {
            this.setBackground(new Color(
                    ChatViewModel.LIGHT_BLUE_RGB[0],
                    ChatViewModel.LIGHT_BLUE_RGB[1],
                    ChatViewModel.LIGHT_BLUE_RGB[2]));

        }
        else
        {
            this.setBackground(new Color(
                    ChatViewModel.LIGHT_GREY_RGB[0],
                    ChatViewModel.LIGHT_GREY_RGB[1],
                    ChatViewModel.LIGHT_GREY_RGB[2]));
        }

        // Set the panel to be opaque to apply the background color
        this.setOpaque(true);
    }
}
