package view.custom_panels;

import interface_adapter.send_message.ChatViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * A panel representing a single message in the ChatView.
 */
public class MessagePanel extends JPanel
{

    public MessagePanel(String sender, String content, String timestamp, boolean isSentByUser)
    {
        // Set panel layout to BoxLayout for vertical stacking of labels
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create labels for sender, content, and timestamp
        JLabel senderLabel = new JLabel(sender);
        senderLabel.setFont(new Font(
                ChatViewModel.MESSAGE_FONT.getName(),
                Font.BOLD,
                ChatViewModel.FONT_SIZE_MESSAGE_SENDER_USERNAME_LABEL));

        JLabel contentLabel = new JLabel("<html><p style=\"width: 200px;\">" + content + "</p></html>");
        contentLabel.setFont(new Font(ChatViewModel.MESSAGE_FONT.getName(),
                ChatViewModel.MESSAGE_FONT.getStyle(),
                ChatViewModel.FONT_SIZE_MESSAGE_CONTENT_LABEL));

        JLabel timestampLabel = new JLabel(timestamp);
        timestampLabel.setFont(new Font(ChatViewModel.MESSAGE_FONT.getName(),
                Font.ITALIC,
                ChatViewModel.FONT_SIZE_MESSAGE_TIMESTAMP_LABEL));

        // Align the panel to the right for sent messages, left for received messages
        if (isSentByUser)
        {
            this.setAlignmentX(Component.RIGHT_ALIGNMENT);
            this.setBackground(new Color(
                    ChatViewModel.LIGHT_BLUE_RGB[0],
                    ChatViewModel.LIGHT_BLUE_RGB[1],
                    ChatViewModel.LIGHT_BLUE_RGB[2]));
        } else
        {
            this.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.setBackground(new Color(
                    ChatViewModel.LIGHT_GREY_RGB[0],
                    ChatViewModel.LIGHT_GREY_RGB[1],
                    ChatViewModel.LIGHT_GREY_RGB[2]));
        }

        // Add padding to the panel for better spacing
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the labels to the panel
        this.add(senderLabel);
        this.add(contentLabel);
        this.add(timestampLabel);

        // Make the panel opaque to apply the background color
        this.setOpaque(true);
    }
}
