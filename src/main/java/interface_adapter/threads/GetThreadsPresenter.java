package interface_adapter.threads;

import java.util.HashMap;
import java.util.Map;

import entity.Thread;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_thread.AddThreadViewModel;
import interface_adapter.chat_refresh.ChatRefreshController;
import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import use_case.get_threads.GetThreadsOutputBoundary;
import use_case.get_threads.GetThreadsOutputData;

/**
 * Presenter for the Get Threads Use Case.
 */
public class GetThreadsPresenter implements GetThreadsOutputBoundary
{
    private final ViewManagerModel viewManagerModel;
    private final ChatViewModel chatViewModel;
    private final ThreadsViewModel threadsViewModel;
    private final AddThreadViewModel addThreadViewModel;
    private final ChatRefreshController chatRefreshController;

    public GetThreadsPresenter(
            ViewManagerModel viewManagerModel,
            ChatViewModel chatViewModel,
            ThreadsViewModel threadsViewModel,
            AddThreadViewModel addThreadViewModel,
            ChatRefreshController chatRefreshController)
    {
        this.viewManagerModel = viewManagerModel;
        this.chatViewModel = chatViewModel;
        this.threadsViewModel = threadsViewModel;
        this.addThreadViewModel = addThreadViewModel;
        this.chatRefreshController = chatRefreshController;
    }

    @Override
    public void prepareSuccessView(GetThreadsOutputData outputData)
    {
        ThreadsState threadsState = threadsViewModel.getState();
        Map<Long, String> threadMap = new HashMap<>();

        // fill the threadMap with thread data
        for (Thread thread : outputData.getThreads())
        {
            threadMap.put(thread.getThreadID(), thread.getName());
        }

        threadsState.setThreadHash(threadMap);
        threadsViewModel.firePropertyChanged("update-thread-list");
    }

    @Override
    public void prepareFailView(String errorMessage)
    {
        ThreadsState threadsState = threadsViewModel.getState();
        threadsState.clearThreadData();
        threadsViewModel.firePropertyChanged("threads-error");
    }

    @Override
    public void switchToChatView(Long threadID)
    {
        // Update chat view model state
        ChatState chatState = chatViewModel.getState();
        chatState.setCurrentThreadID(threadID);
        chatState.setCurrentThreadName(threadsViewModel.getState().getThreadHash().get(threadID));
        chatState.setCurrentUsername(threadsViewModel.getState().getCurrentUsername());
        chatViewModel.setState(chatState);

        // Switch to the chat view
        viewManagerModel.setState(chatViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        // Automatically refresh chat messages
        chatRefreshController.execute(threadID);
    }

    @Override
    public void switchToAddThreadView()
    {
        // This code tells the View Manager to switch to the AddThreads view.
        viewManagerModel.setState(addThreadViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
