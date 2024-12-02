package view.custom_panels;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A panel containing a button, then a text field, then 2 more buttons, in that order.
 */
public class ButtonLabelButtonButtonPanel extends JPanel
{
    public ButtonLabelButtonButtonPanel(JButton button1, JTextField textField, JButton button2, JButton button3)
    {
        this.add(button1);
        this.add(textField);
        this.add(button2);
        this.add(button3);
    }
}
