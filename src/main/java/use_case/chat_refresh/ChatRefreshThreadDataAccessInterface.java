package use_case.chat_refresh;

public interface ChatRefreshUserDataAccessInterface {
    /**
     * Updates the current message thread when the refresh is triggered. If a new message has been sent, it is added to the thread

     */
    void setChat();

}
