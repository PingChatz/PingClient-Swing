package view.custom_panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This creates a component JPanel which is a list of all the threads and their buttons.
 */
public class ThreadsPane extends JScrollPane
{

    private final List<ButtonLabelPanel> buttonLabels = new ArrayList<>();

    public ThreadsPane(String[] threadNames)
    {
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Set common constraints
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        int verticalSpacing = 0; // No extra spacing needed due to margins in ButtonLabelPanel

        if (threadNames.length == 0)
        {
            // Display message when no threads are available
            JLabel noThreadsLabel = new JLabel("There are currently no threads.");
            listPanel.add(noThreadsLabel, gbc);
        } else
        {
            // Add each thread as a panel
            for (String threadName : threadNames)
            {
                ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(threadName));
                buttonLabels.add(panel);

                // Set insets for spacing between panels (if additional spacing is needed)
                gbc.insets = new Insets(0, 0, verticalSpacing, 0);

                listPanel.add(panel, gbc);
            }
        }

        // Add filler to keep components at the top
        gbc.weighty = 1.0;
        listPanel.add(Box.createVerticalGlue(), gbc);

        this.setViewportView(listPanel);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    public final List<ButtonLabelPanel> getButtonLabels()
    {
        return buttonLabels;
    }

    /**
     * Updates the thread pane with an updated list of thread names.
     *
     * @param updatedThreadNames the updated list of thread names.
     */
    public void updateThreadPanel(String[] updatedThreadNames)
    {
        JPanel listPanel = (JPanel) this.getViewport().getView();
        listPanel.removeAll();
        buttonLabels.clear();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        int verticalSpacing = 0; // No extra spacing needed due to margins in ButtonLabelPanel

        if (updatedThreadNames.length == 0)
        {
            // Display message when no threads are available
            JLabel noThreadsLabel = new JLabel("There are currently no threads.");
            listPanel.add(noThreadsLabel, gbc);
        } else
        {
            // Add each thread as a panel
            for (String threadName : updatedThreadNames)
            {
                ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(threadName));
                buttonLabels.add(panel);

                // Set insets for spacing between panels (if additional spacing is needed)
                gbc.insets = new Insets(0, 0, verticalSpacing, 0);

                listPanel.add(panel, gbc);
            }
        }

        // Add filler to keep components at the top
        gbc.weighty = 1.0;
        listPanel.add(Box.createVerticalGlue(), gbc);

        // Refresh the UI
        listPanel.revalidate();
        listPanel.repaint();
    }

    /**
     * Clears the entire threads pane of its data.
     */
    public void resetPane()
    {
        JPanel listPanel = (JPanel) this.getViewport().getView();
        listPanel.removeAll();
        buttonLabels.clear(); // Clear the list of button labels
        listPanel.revalidate();
        listPanel.repaint();
    }
}
