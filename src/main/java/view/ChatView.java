package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import interface_adapter.send_message.SendMessageController;
import view.custom_panels.ButtonLabelButtonButtonPanel;
import view.custom_panels.LabelLabelPanel;
import view.custom_panels.MessageDisplayPanel;

/**
 * The View for when the user is connected with a single Thread (i.e: a Chat).
 */
public class ChatView extends JPanel implements ActionListener, PropertyChangeListener
{
    private final String viewName = "chat";
    private final ChatViewModel chatViewModel;

    private final MessageDisplayPanel messageDisplay;

    private final JButton toThreads;
    private final JTextField messageInputField = new JTextField(15);
    private final JButton send;
    private final JButton refresh;

    private SendMessageController sendMessageController;
    // TODO: add the ChatRefreshController here.

    // == CONSTRUCTOR ==
    public ChatView(ChatViewModel chatViewModel)
    {
        this.chatViewModel = chatViewModel;
        this.chatViewModel.addPropertyChangeListener(this);

        // Initialize the Page Title to be the name of the Thread it represents.
        final JLabel title = new JLabel(chatViewModel.getState().getCurrentThreadName());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize the Message View (the Scrollable JList containing all the message Labels)
        messageDisplay = new MessageDisplayPanel(getMessagePanels());

        // Initialize the Text Entry and Buttons
        this.toThreads = new JButton(ChatViewModel.RETURN_BUTTON_LABEL);
        this.send = new JButton(ChatViewModel.SEND_BUTTON_LABEL);
        this.refresh = new JButton(ChatViewModel.REFRESH_BUTTON_LABEL);

        final ButtonLabelButtonButtonPanel bottom = new ButtonLabelButtonButtonPanel(
                toThreads,
                messageInputField,
                send,
                refresh);

        // Add Action Listener for the send button
        send.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        final ChatState currentState = chatViewModel.getState();
                        sendMessageController.execute(currentState.getMessageInput(),
                                                          currentState.getCurrentThreadID());
                    }
                });

        // Add Action Listener for the toThreads button
        toThreads.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        sendMessageController.switchToThreadsView();
                        System.out.println("toThreads button pressed");
                    }
                }
        );

        // Add Action Listener for the refresh button
        refresh.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        // TODO: button for the refresh use case.
                    }
                }
        );

        // Add listener for the text entry (actual code in helper methods below)
        addTextEntryListener();

        // Set Up the layout for the UI
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(messageDisplay);
        this.add(bottom);
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    }

    /**
     * Returns a list of all the messages currently in this thread (as per the ChatState).
     * @return a list of message panels
     */
    private List<LabelLabelPanel> getMessagePanels()
    {
        final List<LabelLabelPanel> result = new ArrayList<>();
        for (Object[] messageTuple : chatViewModel.getState().getAllMessages())
        {
            final LabelLabelPanel newMessagePanel = new LabelLabelPanel((String) messageTuple[0], messageTuple[1]);
            result.add(newMessagePanel);
        }
        return result;
    }

    /**
     * Listens for changes in the message input field and updates ChatViewState.
     */
    private void addTextEntryListener()
    {
        messageInputField.getDocument().addDocumentListener(new DocumentListener()
        {

            private void documentListenerHelper()
            {
                final ChatState currentState = chatViewModel.getState();
                currentState.setMessageInput(messageInputField.getText());
                chatViewModel.setState(currentState);
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
    public void propertyChange(PropertyChangeEvent evt)
    {
        // Listens for errors
        final ChatState state = (ChatState) evt.getNewValue();
        if (state.getSendMessageError() != null)
        {
            JOptionPane.showMessageDialog(this, state.getSendMessageError());
        }

        // Update text field when state changes
        messageInputField.setText(state.getMessageInput());

        // Update entire UI (only use this in prepareSuccessView() methods for use cases that act on the ChatView)
        if (evt.getPropertyName().equals("full_message_update"))
        {
            messageInputField.setText(state.getMessageInput());
            messageDisplay.updateMessagePanels(getMessagePanels());
        }
    }

    // TODO: figure out what this does
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        System.out.println("Click " + evt.getActionCommand());
    }

    public final String getViewName()
    {
        return viewName;
    }

    public final void setSendMessageController(SendMessageController controller)
    {
        this.sendMessageController = controller;
    }

    // TODO: set ChatRefreshController setter here.

}
