package view.custom_panels;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import interface_adapter.send_message.ChatViewModel;

/**
 * A scrollable panel displaying a list of LabelLabelPanels.
 */
public class MessageDisplayPanel extends JPanel
{
    private final JPanel boxPanel = new JPanel();
    private JScrollPane scrollPane; // Keep a reference to the scroll pane
    public MessageDisplayPanel(List<MessagePanel> messagePanels)
    {
        // Set the layout of boxPanel to BoxLayout for vertical stacking
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

        // Add each message panel to the box panel
        for (MessagePanel messagePanel : messagePanels)
        {
            JPanel wrapperPanel = new JPanel(new FlowLayout(
                    messagePanel.getAlignmentX() == Component.RIGHT_ALIGNMENT ? FlowLayout.RIGHT : FlowLayout.LEFT));
            wrapperPanel.add(messagePanel);
            wrapperPanel.setOpaque(false);
            boxPanel.add(wrapperPanel);
            boxPanel.add(Box.createVerticalStrut(ChatViewModel.MESSAGE_SPACING));
        }

        // Create a JScrollPane to make the box panel scrollable
        scrollPane = new JScrollPane(boxPanel); // Store the scroll pane in an instance variable
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a bordered panel to contain the scroll pane
        JPanel borderedPanel = new JPanel(new BorderLayout());
        borderedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        borderedPanel.add(scrollPane, BorderLayout.CENTER);

        // Set the layout of the main panel to BorderLayout and add the bordered panel
        this.setLayout(new BorderLayout());
        this.add(borderedPanel, BorderLayout.CENTER);
        // Scroll to bottom after initialization
        scrollToBottom();
    }
    // Add a method to access the scroll pane
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    // Add a method to scroll to the bottom
    private void scrollToBottom()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
            }
        });
    }

    /**
     * Updates the MessageDisplayPanel with a new list of message panels.
     *
     * @param updatedMessagePanels the updated list of message panels.
     */
    public void updateMessagePanels(List<MessagePanel> updatedMessagePanels)
    {
        boxPanel.removeAll();
        for (MessagePanel messagePanel : updatedMessagePanels)
        {
            JPanel wrapperPanel = new JPanel(new FlowLayout(
                    messagePanel.getAlignmentX() == Component.RIGHT_ALIGNMENT ? FlowLayout.RIGHT : FlowLayout.LEFT));
            wrapperPanel.add(messagePanel);
            wrapperPanel.setOpaque(false);
            boxPanel.add(wrapperPanel);
            boxPanel.add(Box.createVerticalStrut(10));
        }
        boxPanel.revalidate();
        boxPanel.repaint();


        scrollToBottom();
    }
}
