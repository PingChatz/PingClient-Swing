package view;

import javax.swing.*;

/**
 * This is a JPanel wrapper for a scrollable list of threads. It is placed in a box layout to be one vertical line of boxes.
 */
public class ThreadsPanel extends JPanel
{
    ThreadsPanel(String[] threads)
    {
        this.add(new ThreadsScrollList(threads));
    }
}
