package use_case.chat_refresh;

import java.util.List;


public interface ChatRefreshThreadDataAccessInterface
{
    /**
     * Updates the current message thread when the refresh is triggered. If a new message has been sent, it is added to the thread

     */
    /**
     * Updates the current message thread when the refresh is triggered. If a new message has been sent, it is added to the thread
     */

    List<String[]> getMessageList();
}
