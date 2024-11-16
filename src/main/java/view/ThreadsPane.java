package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This creates a component jPanel which is a list of all of the threads and their buttons.
 */
public class ThreadsPane extends JScrollPane
{

    private ArrayList<ButtonLabelPanel> buttonLabels = new ArrayList<ButtonLabelPanel>();

    public ThreadsPane(String[] threads)
    {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        for (String thread : threads)
        {
            final ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(thread));
            this.buttonLabels.add(panel);
            listPanel.add(panel);
        }
        this.setViewportView(listPanel);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    }

    public ArrayList<ButtonLabelPanel> getButtonLabels()
    {
        return buttonLabels;
    }
}
