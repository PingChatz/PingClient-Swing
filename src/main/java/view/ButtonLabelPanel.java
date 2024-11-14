package view;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * A panel containing a label, then a button.
 */
public class ButtonLabelPanel extends JPanel
{
    private final JButton button = new JButton("view");
    ButtonLabelPanel(JLabel label)
    {
        this.add(label);
        this.add(button);
    }

    public JButton getButton()
    {
        return button;
    }
    public void setActionListener(ActionListener listener){
        button.addActionListener(listener);
    }
}
