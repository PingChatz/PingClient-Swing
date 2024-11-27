package view.custom_panels;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * This creates a component jPanel which is a list of all the threads and their buttons.
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

    public final ArrayList<ButtonLabelPanel> getButtonLabels()
    {
        return buttonLabels;
    }

    /**
     * Updates this thread panel with new data when the threadHash in ThreadsState updates.
     */
    public void updateThreadPanel(String[] updatedThreads)
    {
        // TODO: create the logic that does this
    }
}
