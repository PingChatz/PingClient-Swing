package view.custom_panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A panel containing a button, then a text field, then a button, in that order.
 */
public class ButtonLabelButtonPanel extends JPanel
{
    public ButtonLabelButtonPanel(JButton button1, JTextField textField, JButton button2)
    {
        this.add(button1);
        this.add(textField);
        this.add(button2);
    }
}
