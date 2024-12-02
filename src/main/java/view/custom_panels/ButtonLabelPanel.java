package view.custom_panels;

import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * A panel containing a label, then a button.
 */
public class ButtonLabelPanel extends JPanel
{
    private final JButton button = new JButton("view");
    private final JLabel label;

    public ButtonLabelPanel(JLabel label)
    {
        this.label = label;
        this.add(label);
        this.add(button);
    }

    /**
     * This initialises an actionListener for the button in this panel.
     *
     * @param listener the actionlistener for this button.
     */
    public void setActionListener(ActionListener listener)
    {
        button.addActionListener(listener);
    }

    /**
     * This returns the text content of the label.
     *
     * @return the contents of the label.
     */
    public String getLabelContent()
    {
        return label.getText();
    }
}
