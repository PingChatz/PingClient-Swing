package view.custom_panels;

import javax.swing.*;

/**
 * A panel containing 2 labels.
 */
public class LabelLabelPanel extends JPanel
{
    public LabelLabelPanel(String string1, String string2)
    {
        // TODO: add constants to define it's shape.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel(string1);
        JLabel label2 = new JLabel(string2);
        this.add(label1);
        this.add(label2);
    }
}
