package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.logout.LogoutController;
import interface_adapter.threads.GetThreadsController;
import interface_adapter.threads.ThreadsState;
import interface_adapter.threads.ThreadsViewModel;
import view.custom_panels.ButtonLabelPanel;
import view.custom_panels.ThreadsPane;

/**
 * The view for when the user is logged in and sees all of their message threads.
 */
public class ThreadsView extends JPanel
{

    private final String viewName;
    private final ThreadsViewModel threadsViewModel;

    private final JButton refreshButton;
    private final JButton logoutButton;
    private final ThreadsPane threadsList;

    private GetThreadsController getThreadsController;
    private LogoutController logoutController;

    public ThreadsView(ThreadsViewModel threadsViewModel)
    {
        this.threadsViewModel = threadsViewModel;

        this.viewName = "threads";
        // Initialise the Page Title
        final JLabel titleLabel = new JLabel(ThreadsViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, ThreadsViewModel.TITLE_FONT_SIZE));

        // Initialize the two buttons
        refreshButton = new JButton(ThreadsViewModel.REFRESH_LABEL);
        logoutButton = new JButton(ThreadsViewModel.LOGOUT_LABEL);

        // initialise the list of threads
        final String[] threads = {"Benj", "Ali"};
        threadsViewModel.getState().addThread(1L, "Benj");
        threadsViewModel.getState().addThread(2L, "Ali");

        // TODO replace threads with the actual threads of the user (use case)
        threadsList = new ThreadsPane(threads);
        threadsList.setPreferredSize(new Dimension(ThreadsViewModel.THREADSLIST_WIDTH,
                ThreadsViewModel.THREADSLIST_HEIGHT));

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
                            logoutController.execute(threadsViewModel.getState().getUsername());
                        }
                    }
                }
        );
        // Add an actionlistener for each view buttom in the threads views
        for (ButtonLabelPanel buttonLabel: threadsList.getButtonLabels())
        {
            buttonLabel.setActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent evt)
                {
                    // TODO change to message view for the given message (use case)
                    String threadName = buttonLabel.getLabelContent();
                    for (Long threadIDS : threadsViewModel.getState().getThreadHash().keySet())
                    {
                        if (threadsViewModel.getState().getThreadHash().get(threadIDS).equals(threadName))
                        {
                            getThreadsController.switchToChatView(threadIDS);
                            System.out.println("visiting the thread of " + threadName);
                        }
                    }

                }
            });
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(ThreadsViewModel.TOP_PANEL_LAYOUT_ROWS,
                ThreadsViewModel.TOP_PANEL_LAYOUT_COLUMNS, 0, 0));
        topPanel.add(refreshButton);
        topPanel.add(logoutButton);
        this.add(topPanel);
        this.add(titleLabel);
        this.add(threadsList);
        this.setBorder(BorderFactory.createEmptyBorder(ThreadsViewModel.TOP_BORDER_SPACING,
                ThreadsViewModel.BORDER_SPACING, ThreadsViewModel.BORDER_SPACING, ThreadsViewModel.BORDER_SPACING));
    }

    public final String getViewName()
    {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController)
    {
        this.logoutController = logoutController;
    }

    public void setGetThreadsController(GetThreadsController getThreadsController)
    {
        this.getThreadsController = getThreadsController;
    }
}
