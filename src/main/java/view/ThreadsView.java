package view;
import interface_adapter.threads.ThreadsViewController;
import interface_adapter.threads.ThreadsModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
/**
 * The view for when the user is logged in and sees all of their message threads.
 */
public class ThreadsView extends JPanel implements ActionListener, PropertyChangeListener
{
    private final  String viewName = "threads";
    private final ThreadsModel threadsModel;

    private final JButton refreshButton;
    private final JButton logoutButton;
    private final ThreadsPanel threadsList;



    public ThreadsView(ThreadsModel threadsModel) {
        this.threadsModel = threadsModel;

        // Initialise the Page Title
        final JLabel titleLabel = new JLabel(ThreadsModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize the two buttons
        refreshButton = new JButton(ThreadsModel.REFRESH_LABEL);
        logoutButton = new JButton(ThreadsModel.LOGOUT_LABEL);
        String[] threads = {"Threads", "threads"};

        //initialise the list of threads
        //TODO replace threads with the actual threads of the user
        threadsList = new ThreadsPanel(threads);

        //add action listeners for the buttons
        refreshButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refreshButton)) {
                            //TODO This should somehow call the get messages use case/ api call
                        }
                    }
                }
        );
        logoutButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logoutButton)) {
                            //TODO implementing this for the use case
                            ThreadsViewController.switchToLoginView();
                        }
                    }
                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
