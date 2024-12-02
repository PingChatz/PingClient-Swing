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
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (String threadName : threadNames)
        {
            final ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(threadName));
            this.buttonLabels.add(panel);
            listPanel.add(panel, gbc);
        }
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        listPanel.add(Box.createGlue(), gbc);
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
//
    //public void updateThreadPanel(String[] updatedThreadNames)
//    {
//        JPanel listPanel = (JPanel) this.getViewport().getView();
//
//        // Clear the existing panels
//        listPanel.removeAll();
//        buttonLabels.clear();
//
//        for (String threadName : updatedThreadNames)
//        {
//            ButtonLabelPanel newPanel = new ButtonLabelPanel(new JLabel(threadName));
//            buttonLabels.add(newPanel);
//            listPanel.add(newPanel);
//        }
//
//        // Revalidate and repaint to refresh the UI
//        listPanel.add(Box.createVerticalGlue());
//        listPanel.revalidate();
//        listPanel.repaint();
//    }
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

        for (String threadName : updatedThreadNames)
        {
            ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(threadName));
            buttonLabels.add(panel);
            listPanel.add(panel, gbc);
        }

        // Add filler to keep components at the top
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        listPanel.add(Box.createGlue(), gbc);

        // Refresh the UI
        listPanel.revalidate();
        listPanel.repaint();
    }
    public void resetPane()
    {
        JPanel listPanel = (JPanel) this.getViewport().getView();
        listPanel.removeAll();
        this.removeAll();
        listPanel.revalidate();
        listPanel.repaint();
    }
}
