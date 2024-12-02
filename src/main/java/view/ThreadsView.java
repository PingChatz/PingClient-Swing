package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import java.awt.*;

import interface_adapter.logout.LogoutController;
import interface_adapter.threads.GetThreadsController;
import interface_adapter.threads.ThreadsState;
import interface_adapter.threads.ThreadsViewModel;
import view.custom_panels.ButtonLabelPanel;
import view.custom_panels.ThreadsPane;

/**
 * The view for when the user is logged in and sees all of their message threads.
 */
public class ThreadsView extends JPanel implements PropertyChangeListener
{

    private final String viewName;
    private final ThreadsViewModel threadsViewModel;

    private final JButton refreshButton;
    private final JButton logoutButton;
    private final JButton addThreadButton;
    private final ThreadsPane threadsList;

    private GetThreadsController getThreadsController;
    private LogoutController logoutController;

    public ThreadsView(ThreadsViewModel threadsViewModel)
    {
        this.threadsViewModel = threadsViewModel;
        this.threadsViewModel.addPropertyChangeListener(this);

        this.viewName = "threads";
        // Initialise the Page Title
        final JLabel titleLabel = new JLabel(ThreadsViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, ThreadsViewModel.TITLE_FONT_SIZE));

        // Initialize the three buttons
        refreshButton = new JButton(ThreadsViewModel.REFRESH_LABEL);
        logoutButton = new JButton(ThreadsViewModel.LOGOUT_LABEL);
        addThreadButton = new JButton(ThreadsViewModel.ADDTHREAD_LABEL);

        // Initialise the list of threadNames (will be empty at construction)
        final String[] threadNames = threadsViewModel.getState().getThreadNamesList();
        threadsList = new ThreadsPane(threadNames);
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
                            if (getThreadsController != null)
                            {
                                String username = threadsViewModel.getState().getCurrentUsername();
                                getThreadsController.execute(username);
                            } else
                            {
                                System.out.println("Error: GetThreadsController is not set.");
                            }
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
                            logoutController.execute();
                        }
                    }
                }
        );

        addThreadButton.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        if (evt.getSource().equals(addThreadButton))
                        {
                            getThreadsController.switchToAddThreadView();
                        }
                    }
                }
        );

        // TODO: remove this comment, just put it so that people know why I removed the action listener initialization
        // action listeners for each view button in the threads views are not updated here, since there are none
        // at initialization.

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(ThreadsViewModel.TOP_PANEL_LAYOUT_ROWS,
                ThreadsViewModel.TOP_PANEL_LAYOUT_COLUMNS, 0, 0));
        topPanel.add(refreshButton);
        topPanel.add(logoutButton);
        topPanel.add(addThreadButton);
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

    public final void setLogoutController(LogoutController logoutController)
    {
        this.logoutController = logoutController;
    }

    public final void setGetThreadsController(GetThreadsController getThreadsController)
    {
        this.getThreadsController = getThreadsController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        // Listens for changes in the state and update view accordingly
        final ThreadsState state = (ThreadsState) evt.getNewValue();

        // Set threads list to an updated one (fired when add thread use case or the refresh use case is successful)
        if (evt.getPropertyName().equals("update-thread-list"))
        {
            final String[] updatedThreadNames = state.getThreadNamesList();
            threadsList.updateThreadPanel(updatedThreadNames);

            // Update action listeners for each view button in the threads views
            for (ButtonLabelPanel buttonLabel : threadsList.getButtonLabels())
            {
                buttonLabel.setActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        String threadName = buttonLabel.getLabelContent();

                        for (Long threadID : threadsViewModel.getState().getThreadHash().keySet())
                        {
                            if (threadsViewModel.getState().getThreadHash().get(threadID).equals(threadName))
                            {
                                getThreadsController.switchToChatView(threadID);
                                System.out.println("Switched to thread: " + threadName);
                            }
                        }
                    }
                });
            }
        }
    }
}
