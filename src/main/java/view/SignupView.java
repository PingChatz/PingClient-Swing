package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements ActionListener, PropertyChangeListener
{
    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private SignupController signupController;

    private final JButton signUp;
    private final JButton cancel;
    private final JButton toLogin;

    // Constructor
    public SignupView(SignupViewModel signupViewModel)
    {
        this.signupViewModel = signupViewModel;

        // Initialize the Page Title
        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize the User Information ( text Entries)
        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        // Initialize Buttons
        final JPanel buttons = new JPanel();
        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        cancel = new JButton(SignupViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        // Add Action listeners for the buttons
        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        if (evt.getSource().equals(signUp))
                        {
                            final SignupState currentState = signupViewModel.getState();
                            // TODO: Uncomment when sign Up UseCase is ready

//                          signupController.execute(
//                          //        currentState.getUsername(),
//                                    currentState.getPassword(),
//                                    currentState.getRepeatPassword()
//                            );
                        }
                    }
                }
        );

        toLogin.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        signupController.switchToLoginView();
                    }
                }
        );

        cancel.addActionListener(this);

        // Add listeners for the text entries
        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        // Set Up the layout for the UI
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);

        // Add margin of 40 pixels on all sides
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    }

    /**
     * Listens to the changes in teh username field and updates the current state.
     */
    private void addUsernameListener()
    {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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
     * Listens to the Password field and if a change is made updates the values in sign Up state.
     */
    private void addPasswordListener()
    {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
     * Listens for updates in the repeat password field, and then updates the values of signupViewModel.
     */
    private void addRepeatPasswordListener()
    {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
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
    public void actionPerformed(ActionEvent actionEvent)
    {
        // TODO: change to do something
        JOptionPane.showMessageDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null)
        {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public final String getViewName()
    {
        return viewName;
    }

    public final void setSignupController(SignupController controller)
    {
        this.signupController = controller;
    }
}
