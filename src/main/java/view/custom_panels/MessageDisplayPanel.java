package view.custom_panels;

import javax.swing.JList;
import javax.swing.JPanel;

/**
 * A panel containing a JList of LabelLabelPanels.
 */
public class MessageDisplayPanel extends JPanel
{
    public MessageDisplayPanel(JList<LabelLabelPanel> listOfMessages)
    {
        this.add(listOfMessages);
    }

}
