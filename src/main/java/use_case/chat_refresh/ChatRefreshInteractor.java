package use_case.chat_refresh;
import data_access.ThreadDataAccessObject;
import entity.Message;
import interface_adapter.chat_refresh.ChatRefreshPresenter;
import interface_adapter.send_message.ChatState;
import org.checkerframework.checker.units.qual.A;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

    public class ChatRefreshInteractor implements ChatRefreshInputBoundary {
        //intsance variables
        private final ThreadDataAccessObject threadDataAccessObject;
        //private final ChatRefreshOutputBoundary ChatRefreshPresenter;

        private final ChatRefreshPresenter chatRefreshPresenter;
        public ChatRefreshInteractor(data_access.ThreadDataAccessObject threadDataAccessObject, ChatRefreshPresenter chatRefreshPresenter) {
            this.threadDataAccessObject = threadDataAccessObject;

            this.chatRefreshPresenter = chatRefreshPresenter;
        }

        // == USE CASE METHODS ==
        @Override
        public void execute() {
            // updates chat message
            // final List<Message> messageList = threadDataAccessObject.getMessageList();
            List<Message> messageList = new ArrayList<>();

            List<String[]> messages = new ArrayList<>();
            for (Message message : messageList) {
                String[] lst = {message.getContent().toString(), message.getSenderUsername().toString()};
                messages.add(lst);

            }

            final ChatRefreshOutputData chatRefreshOutputData = new ChatRefreshOutputData(messages);
            this.chatRefreshPresenter.prepareSucessView(chatRefreshOutputData);

        }
}
