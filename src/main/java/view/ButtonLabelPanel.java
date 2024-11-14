package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel containing a label, then a button.
 */
public class ButtonLabelPanel extends JPanel
{
    private final JButton button = new JButton("view");
    private JLabel label = new JLabel();

    ButtonLabelPanel(JLabel label)
    {
        this.label = label;
        this.add(label);
        this.add(this.label);
    }

    /**
     * This initialises an actionListener for the button in this panel.
     * @param listener the actionlistener for this button.
     */
    public void setActionListener(ActionListener listener)
    {
        button.addActionListener(listener);
    }

    /**
     * This returns the text content of the label.
     * @return the contents of the label.
     */
    public String getLabelContent()
    {
        return label.getText();
    }
}
