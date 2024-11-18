package view.custom_panels;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel containing two labels.
 */
public class LabelLabelPanel extends JPanel
{

    public LabelLabelPanel(JLabel label1, JLabel label2)
    {
        this.add(label1);
        this.add(label2);
    }
}
