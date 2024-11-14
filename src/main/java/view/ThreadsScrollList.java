package view;

import javax.swing.*;
import java.awt.*;

public class ThreadsScrollList extends JPanel{
    private JButton[] buttons;
    ThreadsScrollList(String[] threads)
    {
        setLayout(new ScrollPaneLayout());
        for (String thread : threads) {
            ButtonLabelPanel buttonLabel = new ButtonLabelPanel(new JLabel(thread));
            this.add(buttonLabel);
        }
    }
}
