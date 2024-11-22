package view.custom_panels;

import javax.swing.*;

/**
 * A panel containing 2 labels.
 * NOTE: for the sake of our program, the first label represents the sender of a message, and the second represents
 *       it's content.
 */
public class LabelLabelPanel extends JPanel
{
    public LabelLabelPanel(String string1, Object string2)
    {
        // TODO: add constants to define it's shape.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel(string1);
        // TODO: temporarily cast the second JLabel to a String, find a better fix later.
        JLabel label2 = new JLabel((String) string2);
        this.add(label1);
        this.add(label2);
    }
}
