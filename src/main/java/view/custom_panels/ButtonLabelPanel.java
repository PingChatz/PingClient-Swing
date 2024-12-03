package view.custom_panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * A panel containing a label and a button, styled and aligned properly.
 */
public class ButtonLabelPanel extends JPanel
{
    private final JButton button = new JButton("view");
    private final JLabel label;

    public ButtonLabelPanel(JLabel label)
    {
        this.label = label;

        // Set layout to GridBagLayout for precise control
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configure label constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(label, gbc);

        // Configure button constraints
        gbc.gridx = 1;
        gbc.weightx = 0.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        this.add(button, gbc);

        // Define margins and padding
        int panelTopMargin = 5;
        int panelLeftRightMargin = 10;
        int panelBottomMargin = 5;

        int padding = 10;

        // Create the borders
        Border margins = BorderFactory.createEmptyBorder(
                panelTopMargin, panelLeftRightMargin, panelBottomMargin, panelLeftRightMargin);
        Border lineWithPadding = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(padding, padding, padding, padding)
        );

        // Set the panel's border to include margins, line border, and padding
        this.setBorder(BorderFactory.createCompoundBorder(margins, lineWithPadding));

        // Add left margin to the label (adjustable)
        int labelLeftMargin = 5;
        label.setBorder(BorderFactory.createEmptyBorder(0, labelLeftMargin, 0, 0));
    }

    /**
     * Initializes an ActionListener for the button in this panel.
     *
     * @param listener the ActionListener for this button.
     */
    public void setActionListener(ActionListener listener)
    {
        // Remove any existing listeners to prevent duplicates
        for (ActionListener al : button.getActionListeners())
        {
            button.removeActionListener(al);
        }
        button.addActionListener(listener);
    }

    /**
     * Returns the text content of the label.
     *
     * @return the contents of the label.
     */
    public String getLabelContent()
    {
        return label.getText();
    }
}
