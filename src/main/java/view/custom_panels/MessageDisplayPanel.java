package view.custom_panels;

import java.awt.*;
import java.util.List;

import javax.swing.*;

/**
 * A scrollable panel displaying a list of LabelLabelPanels.
 */
public class MessageDisplayPanel extends JPanel
{

    public MessageDisplayPanel(List<LabelLabelPanel> messagePanels)
    {
        // Create a panel with BoxLayout to hold the message panels
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

        // Add each message panel to the box panel
        for (LabelLabelPanel messagePanel : messagePanels)
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
}
