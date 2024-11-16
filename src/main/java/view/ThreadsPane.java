package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This creates a component JPanel which is a list of all the threads and their buttons.
 */
public class ThreadsPane extends JPanel {

    private final List<ButtonLabelPanel> buttonLabels = new ArrayList<>();

    public ThreadsPane(String[] threads) {
        for (String thread : threads) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            final ButtonLabelPanel panel = new ButtonLabelPanel(new JLabel(thread));
            buttonLabels.add(panel);
            add(panel);
        }

    }

    public final List<ButtonLabelPanel> getButtonLabels()
    {
        return buttonLabels;
    }
}
