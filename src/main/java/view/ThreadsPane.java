package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This creates a component jPanel which is a list of all of the threads and their buttons.
 */
public class ThreadsPane extends JPanel {

    private ButtonLabelPanel[] buttonLabels;

    public ThreadsPane(String[] threads) {
        for (int i = 0; i < threads.length; i++) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            final ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(threads[i]));
            buttonLabels[i] = panel;
            add(panel);
        }

    }

    public ButtonLabelPanel[] getButtonLabels()
    {
        return buttonLabels;
    }
}
