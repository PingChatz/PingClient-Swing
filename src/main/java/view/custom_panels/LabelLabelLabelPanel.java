package view.custom_panels;

import javax.swing.*;

/**
 * A panel containing 2 labels.
 */
public class LabelLabelLabelPanel extends JPanel
{
    public LabelLabelLabelPanel(String string1, String string2, String string3)
    {
        // TODO: add constants to define it's shape.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel(string1);
        JLabel label2 = new JLabel(string2);
        JLabel label3 = new JLabel(string3);
        this.add(label1);
        this.add(label2);
        this.add(label3);
    }
}
