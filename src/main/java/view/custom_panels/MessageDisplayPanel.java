package view.custom_panels;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A scrollable panel displaying a list of LabelLabelPanels.
 */
public class MessageDisplayPanel extends JPanel
{
    private final JPanel boxPanel = new JPanel();

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
            wrapperPanel.setOpaque(false); // Transparent background
            boxPanel.add(wrapperPanel);
            boxPanel.add(Box.createVerticalStrut(10)); // Spacing between messages
        }

        // Create a JScrollPane to make the box panel scrollable
        JScrollPane scrollPane = new JScrollPane(boxPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Create a bordered panel to contain the scroll pane
        JPanel borderedPanel = new JPanel(new BorderLayout());
        borderedPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        borderedPanel.add(scrollPane, BorderLayout.CENTER);

        // Set the layout of the main panel to BorderLayout and add the bordered panel
        this.setLayout(new BorderLayout());
        this.add(borderedPanel, BorderLayout.CENTER);
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
    }
}
