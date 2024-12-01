package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import view.custom_panels.LabelTextPanel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener
{

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton cancel;
    private LoginController loginController;

    // Constructor
    public LoginView(LoginViewModel loginViewModel)
    {

        // Initialize the Page Title
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        final JPanel buttons = new JPanel();
        logIn = new JButton("log in");
        buttons.add(logIn);
        cancel = new JButton("cancel");
        buttons.add(cancel);

        logIn.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        if (evt.getSource().equals(logIn))
                        {
                            final LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsernameOrEmail(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        if (evt.getSource().equals(cancel))
                        {
                            final LoginState currentState = loginViewModel.getState();
                            currentState.setUsernameOrEmail("");
                            currentState.setPassword("");
                            loginViewModel.setState(currentState);
                            loginViewModel.firePropertyChanged();
                            loginController.switchToSignUpView();
                        }
                    }
                }
        );

        usernameInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsernameOrEmail(usernameInputField.getText());
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                documentListenerHelper();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                documentListenerHelper();
            }
        });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(buttons);

        // Add margin of 40 pixels on all sides
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    }

    /**
     * React to a button click that results in evt.
     *
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        usernameErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state)
    {
        usernameInputField.setText(state.getUsernameOrEmail());
        passwordInputField.setText(state.getPassword());
    }

    public final String getViewName()
    {
        return viewName;
    }

    public final void setLoginController(LoginController loginController)
    {
        this.loginController = loginController;
    }
}
