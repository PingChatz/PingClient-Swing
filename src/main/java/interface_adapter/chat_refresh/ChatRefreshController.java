package interface_adapter.chat_refresh;

import use_case.chat_refresh.ChatRefreshInputBoundary;

public class ChatRefreshController {
    private final ChatRefreshInputBoundary chatRefreshUseCaseInteractor;

    public ChatRefreshController(ChatRefreshInputBoundary chatRefreshUseCaseInteractor1) {
        this.chatRefreshUseCaseInteractor = chatRefreshUseCaseInteractor1;

    }

    // exectutes the interactor
    public void execute(){
        chatRefreshUseCaseInteractor.execute();
    }
}
