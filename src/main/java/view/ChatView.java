package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import interface_adapter.send_message.SendMessageController;
import view.custom_panels.ButtonLabelButtonPanel;
import view.custom_panels.LabelLabelPanel;
import view.custom_panels.MessageDisplayPanel;

/**
 * The View for when the user is connected with a single Thread (i.e: a Chat).
 */
public class ChatView extends JPanel implements ActionListener, PropertyChangeListener
{
    // == INSTANCE VARIABLES==

    // TODO: figure out how to get the data from Thread
    private final String viewName = "[name-of-thread]";
    private final ChatViewModel chatViewModel;

    private final JList<LabelLabelPanel> listOfMessages = new JList<>();

    private final JButton toThreads;
    private final JTextField messageInputField = new JTextField(15);
    private final JButton send;

    private SendMessageController sendMessageController;

    // == CONSTRUCTOR ==
    public ChatView(ChatViewModel chatViewModel)
    {
        this.chatViewModel = chatViewModel;

        // Initialize the Page Title
        final JLabel title = new JLabel(ChatViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize the Message View (the Scrollable JList containing all the message Labels)
        // TODO: update listOfMessages with necessary data from Thread
        final MessageDisplayPanel messageDisplay = new MessageDisplayPanel(listOfMessages);

        // Initialize the Text Entry and Buttons
        this.toThreads = new JButton(ChatViewModel.RETURN_BUTTON_LABEL);
        this.send = new JButton(ChatViewModel.SEND_BUTTON_LABEL);

        final ButtonLabelButtonPanel bottom = new ButtonLabelButtonPanel(
                toThreads,
                messageInputField,
                send);

        // Add Action Listener for the send button
        send.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        if (evt.getSource().equals(send))
                        {
                            final ChatState currentState = chatViewModel.getState();
                            // TODO: Add the chat Controller state updater when building the use-case.
                        }
                    }
                });

        // Add Action Listener for the toThreads button
        toThreads.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent evt)
                    {
                        // TODO: this button should just switch the view back to the ThreadView.
                        sendMessageController.switchToThreadView();
                    }
                }
        );

        // Add listeners for the text entries (actual code in helper methods below)
        addTextEntryListener();

        // Set Up the layout for the UI
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(messageDisplay);
        this.add(bottom);
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
    }

    /**
     * Listens for changes in the message input field and updates the current state.
     */
    private void addTextEntryListener()
    {
        // TODO: write this when making the use-case (TIP: use the code from the listeners in other Views)
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        // TODO: write this when making the use-case.
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        // TODO: write this when making the use-case.
    }

    public final String getViewName()
    {
        return viewName;
    }

    public final void setSendMessageController(SendMessageController controller)
    {
        this.sendMessageController = controller;
    }

}
