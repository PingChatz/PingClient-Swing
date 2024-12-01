package view;


import interface_adapter.add_thread.AddThreadController;
import interface_adapter.add_thread.AddThreadState;
import interface_adapter.add_thread.AddThreadViewModel;
import view.custom_panels.LabelTextPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Add Thread Use Case.
 */
public class AddThreadView extends JPanel implements ActionListener, PropertyChangeListener
{
    private final String viewName = "add thread";

    private final AddThreadViewModel addThreadViewModel;
    private final JTextField threadNameInputField = new JTextField(15);
    private final JTextField usernameListInputField = new JTextField(15);
    private final JButton addThread;
    private final JButton toThreads;
    private AddThreadController addThreadController;

    public AddThreadView(AddThreadViewModel addThreadViewModel)
    {
        this.addThreadViewModel = addThreadViewModel;
        this.addThreadViewModel.addPropertyChangeListener(this);

        // Initialize the Page Title
        final JLabel title = new JLabel(AddThreadViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize text entries
        final LabelTextPanel threadNameInfo = new LabelTextPanel(
                new JLabel(AddThreadViewModel.THREAD_NAME_LABEL), threadNameInputField);
        final LabelTextPanel usersListInfo = new LabelTextPanel(
                new JLabel(AddThreadViewModel.USERS_LIST_LABEL), usernameListInputField);

        // Initialize Buttons
        final JPanel buttons = new JPanel();
        toThreads = new JButton(AddThreadViewModel.TO_THREADS_BUTTON_LABEL);
        buttons.add(toThreads);
        addThread = new JButton(AddThreadViewModel.ADD_THREAD_BUTTON_LABEL);
        buttons.add(addThread);

        // Add Action listener for the toThreads button
        toThreads.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        addThreadController.switchToThreadsView();
                    }
                }
        );

        // Add Action listener for the addThread button.
        addThread.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        final AddThreadState currentState = addThreadViewModel.getState();
                        addThreadController.execute(
                                currentState.getThreadName(),
                                currentState.getUsernameList(),
                                currentState.getCurrentUsername());
                    }
                }
        );

        // Add listeners for the text entries
        threadNameListener();
        usersListListener();

        // Set Up the layout for the UI
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(threadNameInfo);
        this.add(usersListInfo);
        this.add(buttons);

        // Add margin of BORDER_DIMENSIONS pixels on all sides
        this.setBorder(BorderFactory.createEmptyBorder(
                AddThreadViewModel.BORDER_DIMENSIONS,
                AddThreadViewModel.BORDER_DIMENSIONS,
                AddThreadViewModel.BORDER_DIMENSIONS,
                AddThreadViewModel.BORDER_DIMENSIONS
        ));
    }

    /**
     * Listens to the changes in the thread name field and updates the current state.
     */
    private void threadNameListener()
    {
        threadNameInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final AddThreadState currentState = addThreadViewModel.getState();
                currentState.setThreadName(threadNameInputField.getText());
                addThreadViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                // Change if a new value is added
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                // Change if a value is removed
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                // Change if a value is changed
                documentListenerHelper();
            }
        });
    }

    /**
     * Listens to the changes in the users list field and updates the current state.
     */
    private void usersListListener()
    {
        usernameListInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final AddThreadState currentState = addThreadViewModel.getState();
                currentState.setUsernameList(usernameListInputField.getText());
                addThreadViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                // Change if a new value is added
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                // Change if a value is removed
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                // Change if a value is changed
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        // check for errors
        final AddThreadState state = (AddThreadState) evt.getNewValue();
        if (state.getAddThreadError() != null)
        {
            JOptionPane.showMessageDialog(this, state.getAddThreadError());
        }
        if (evt.getPropertyName().equals("reset-success"))
        {
            JOptionPane.showMessageDialog(this, state.getAddThreadSuccess());
            threadNameInputField.setText(state.getThreadName());
            usernameListInputField.setText(state.getUsernameList());
        }
    }

    public final String getViewName()
    {
        return viewName;
    }

    public final void setAddThreadController(AddThreadController controller)
    {
        this.addThreadController = controller;
    }
}
