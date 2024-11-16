package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.threads.ThreadsViewModel;
import interface_adapter.threads.ThreadsViewController;

/**
 * The view for when the user is logged in and sees all of their message threads.
 */
public class ThreadsView extends JPanel implements ActionListener, PropertyChangeListener
{
    private final String viewName;
    private final ThreadsViewModel threadsViewModel;

    private final JButton refreshButton;
    private final JButton logoutButton;
    private final ThreadsPane threadsList;

    private ThreadsViewController threadsViewController;

    public ThreadsView(ThreadsViewModel threadsViewModel)
    {
        this.threadsViewModel = threadsViewModel;

        this.viewName = "threads";
        // Initialise the Page Title
        final JLabel titleLabel = new JLabel(ThreadsViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Initialize the two buttons
        refreshButton = new JButton(ThreadsViewModel.REFRESH_LABEL);
        logoutButton = new JButton(ThreadsViewModel.LOGOUT_LABEL);

        // initialise the list of threads
        final String[] threads = new String[40];

        // TODO replace threads with the actual threads of the user (use case)
        threadsList = new ThreadsPane(threads);
        threadsList.setPreferredSize(new Dimension(400,300));

        // add action listeners for the buttons
        refreshButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        if (evt.getSource().equals(refreshButton))
                        {
                            // TODO This should somehow call the get messages use case/ api call (use case)
                        }
                    }
                }
        );
        logoutButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        if (evt.getSource().equals(logoutButton))
                        {
                            // TODO implementing this for the use case
                            threadsViewController.switchToLoginView();
                        }
                    }
                }
        );

        for (ButtonLabelPanel buttonLabel: threadsList.getButtonLabels())
        {
            buttonLabel.setActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    // TODO change to message view for the given message (use case)
                    String threadName = buttonLabel.getLabelContent();
                    // this was to suppress checkstyle
                    System.out.println(threadName);
                    threadsViewController.switchToChatView();
                }
            });
        }
        // TODO assemble the final view UI
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2, 0, 0));
        topPanel.add(refreshButton);
        topPanel.add(logoutButton);
        this.add(topPanel);
        this.add(titleLabel);
        this.add(threadsList);
        this.setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));
    }

    public final String getViewName()
    {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    // TODO  setThreadsViewController like in sendMessageView
}
