package view;

import interface_adapter.chat_refresh.ChatRefreshController;
import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import interface_adapter.send_message.SendMessageController;
import view.custom_panels.ButtonLabelButtonButtonPanel;
import view.custom_panels.MessageDisplayPanel;
import view.custom_panels.MessagePanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

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

    private ChatRefreshController chatRefreshController;
    private SendMessageController sendMessageController;

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
                        sendMessageController.execute(
                                currentState.getMessageInput(),
                                currentState.getCurrentThreadID(),
                                currentState.getCurrentUsername());
                    }
                });

        // Add Action Listener for the toThreads button
        toThreads.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        sendMessageController.switchToThreadsView();
                    }
                }
        );

        // Add Action Listener for the refresh button
        // Set up the refresh button
        refresh.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                ChatState currentState = chatViewModel.getState();
                chatRefreshController.execute(currentState.getCurrentThreadID());
            }
        });

        // Add listener for the text entry (actual code in helper methods below)
        addTextEntryListener();

        // Set Up the layout for the UI
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(messageDisplay);
        this.add(bottom);
        this.setBorder(BorderFactory.createEmptyBorder(
                ChatViewModel.BORDER_DIMENSIONS,
                ChatViewModel.BORDER_DIMENSIONS,
                ChatViewModel.BORDER_DIMENSIONS,
                ChatViewModel.BORDER_DIMENSIONS));
    }

    public void setChatRefreshController(ChatRefreshController chatRefreshController)
    {
        this.chatRefreshController = chatRefreshController;
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
        String propertyName = evt.getPropertyName();
        if ("full_message_update".equals(propertyName))
        {
            messageDisplay.updateMessagePanels(getMessagePanels());
        }
        if ("message_update".equals(propertyName))
        {
            final ChatState state = (ChatState) evt.getNewValue();
            messageInputField.setText(state.getMessageInput());
            messageDisplay.updateMessagePanels(getMessagePanels());
        }
        else
        {
            // Handle other property changes
            final ChatState state = (ChatState) evt.getNewValue();
            if (state.getSendMessageError() != null)
            {
                JOptionPane.showMessageDialog(this, state.getSendMessageError());
            }
            // Update text field when state changes
            messageInputField.setText(state.getMessageInput());
        }
    }

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

    public List<MessagePanel> getMessagePanels()
    {
        List<MessagePanel> result = new ArrayList<>();
        for (String[] messageTuple : chatViewModel.getState().getAllMessages())
        {
            // Determine if the message is sent by the current user
            boolean isSentByUser = messageTuple[0].equals(chatViewModel.getState().getCurrentUsername());

            // Create a new MessagePanel with appropriate alignment
            MessagePanel newMessagePanel = new MessagePanel(
                    messageTuple[0], messageTuple[1], messageTuple[2], isSentByUser);
            result.add(newMessagePanel);
        }
        return result;
    }

}
