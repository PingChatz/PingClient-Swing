package view.custom_panels;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This creates a component jPanel which is a list of all of the threads and their buttons.
 */
public class ThreadsPane extends JScrollPane
{

    private ArrayList<ButtonLabelPanel> buttonLabels = new ArrayList<ButtonLabelPanel>();
    private JPanel listPanel ;

    public ThreadsPane(String[] threads)
    {
        this.listPanel = new JPanel();
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

    public void addThread(String thread)
    {
        ButtonLabelPanel newPanel = new ButtonLabelPanel(new JLabel(thread));
        this.buttonLabels.add(newPanel);
        listPanel.add(newPanel);
        this.setViewportView(listPanel);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listPanel.revalidate();
        listPanel.repaint();
    }
}
