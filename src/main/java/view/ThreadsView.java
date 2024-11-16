package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.threads.ThreadsViewModel;
import interface_adapter.threads.ThreadsViewController;

/**
 * The view for when the user is logged in and sees all of their message threads.
 */
public class ThreadsView extends JPanel implements ActionListener, PropertyChangeListener
{
    private final String viewName = "threads";
    private final ThreadsViewModel threadsViewModel;

    private final JButton refreshButton;
    private final JButton logoutButton;
    private final ThreadsPane threadsList;

    private ThreadsViewController threadsViewController;

    public ThreadsView(ThreadsViewModel threadsViewModel) {
        this.threadsViewModel = threadsViewModel;

        // Initialise the Page Title
        final JLabel titleLabel = new JLabel(ThreadsViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize the two buttons
        refreshButton = new JButton(ThreadsViewModel.REFRESH_LABEL);
        logoutButton = new JButton(ThreadsViewModel.LOGOUT_LABEL);
        final String[] threads = {"Threads", "threads"};

        // initialise the list of threads
        // TODO replace threads with the actual threads of the user (use case)
        threadsList = new ThreadsPane(threads);

        // add action listeners for the buttons
        refreshButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refreshButton)) {
                            // TODO This should somehow call the get messages use case/ api call (use case)
                        }
                    }
                }
        );
        logoutButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logoutButton)) {
                            // TODO implementing this for the use case
                            threadsViewController.switchToLoginView();
                        }
                    }
                }
        );

        for (ButtonLabelPanel buttonLabel: threadsList.getButtonLabels())
        {
            buttonLabel.setActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    // TODO change to message view for the given message (use case)
                    String threadName = buttonLabel.getLabelContent();
                    // this was to suppress checkstyle
                    System.out.println(threadName);
                    threadsViewController.switchToChatView();
                }
            });
        }
        // TODO assemble the final view UI
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    // TODO implement getViewName and setThreadsViewController like in sendMessageView
}
