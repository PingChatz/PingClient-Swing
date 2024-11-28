package use_case.chat_refresh;

import java.util.List;

public class ChatRefreshOutputData {
    private final List<String[]> messages;


    public ChatRefreshOutputData(List<String[]> messages) {
        this.messages = messages;

    }

    public List<String[]> get_messages() {
        return messages;
    }

}
