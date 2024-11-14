package view;

import javax.swing.JList;
import javax.swing.JPanel;

/**
 * A panel containing a JList of LabelLabelPanels.
 */
public class MessageDisplayPanel extends JPanel
{
    MessageDisplayPanel(JList<LabelLabelPanel> listOfMessages)
    {
        this.add(listOfMessages);
    }

}
