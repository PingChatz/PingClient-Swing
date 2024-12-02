package view.custom_panels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * This creates a component JPanel which is a list of all the threads and their buttons.
 */
public class ThreadsPane extends JScrollPane
{

    private final List<ButtonLabelPanel> buttonLabels = new ArrayList<>();

    public ThreadsPane(String[] threadNames)
    {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        for (String threadName : threadNames)
        {
            final ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(threadName));
            this.buttonLabels.add(panel);
            listPanel.add(panel);
        }
        this.setViewportView(listPanel);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    }

    public final List<ButtonLabelPanel> getButtonLabels()
    {
        return buttonLabels;
    }

    /**
     * Updates the thread pane with abn updated list of thread names.
     *
     * @param updatedThreadNames the updated list of thread names
     */
    public void updateThreadPanel(String[] updatedThreadNames)
    {
        JPanel listPanel = (JPanel) this.getViewport().getView();

        // Clear the existing panels
        listPanel.removeAll();
        buttonLabels.clear();

        for (String threadName : updatedThreadNames)
        {
            ButtonLabelPanel newPanel = new ButtonLabelPanel(new JLabel(threadName));
            buttonLabels.add(newPanel);
            listPanel.add(newPanel);
        }

        // Revalidate and repaint to refresh the UI
        listPanel.revalidate();
        listPanel.repaint();
    }
}
