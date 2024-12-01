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

    public MessageDisplayPanel(List<LabelLabelLabelPanel> messagePanels)
    {
        // Set the layout of boxPanel
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

        // Add each message panel to the box panel
        for (LabelLabelLabelPanel messagePanel : messagePanels)
        {
            boxPanel.add(messagePanel);
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
    public void updateMessagePanels(List<LabelLabelLabelPanel> updatedMessagePanels)
    {
        boxPanel.removeAll();
        for (LabelLabelLabelPanel messagePanel : updatedMessagePanels)
        {
            boxPanel.add(messagePanel);
        }
        boxPanel.revalidate();
        boxPanel.repaint();
    }
}
