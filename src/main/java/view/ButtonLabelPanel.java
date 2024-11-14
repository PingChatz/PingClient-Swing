package view;
import javax.swing.*;
import java.awt.event.ActionListener;

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

    public JButton getButton()
    {
        return button;
    }
    public void setActionListener(ActionListener listener){
        button.addActionListener(listener);
    }
    public String getLabelContent()
    {
        return label.getText();
    }
}
