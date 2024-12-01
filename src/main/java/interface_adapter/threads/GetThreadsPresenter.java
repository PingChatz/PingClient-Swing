package interface_adapter.threads;

import entity.Thread;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_thread.AddThreadState;
import interface_adapter.add_thread.AddThreadViewModel;
import interface_adapter.send_message.ChatState;
import interface_adapter.send_message.ChatViewModel;
import use_case.get_threads.GetThreadsOutputBoundary;
import use_case.get_threads.GetThreadsOutputData;

import java.util.HashMap;
import java.util.Map;

public class GetThreadsPresenter implements GetThreadsOutputBoundary
{
    private ViewManagerModel viewManagerModel;
    private ChatViewModel chatViewModel;
    private ThreadsViewModel threadsViewModel;
    private AddThreadViewModel addThreadViewModel;

    public GetThreadsPresenter(ViewManagerModel viewManagerModel, ChatViewModel chatViewModel, ThreadsViewModel threadsViewModel, AddThreadViewModel addThreadViewModel)
    {
        this.viewManagerModel = viewManagerModel;
        this.chatViewModel = chatViewModel;
        this.threadsViewModel = threadsViewModel;
        this.addThreadViewModel = addThreadViewModel;
    }

    @Override
    public void prepareSuccessView(GetThreadsOutputData outputData)
    {
        ThreadsState threadsState = threadsViewModel.getState();
        Map<Long, String> threadMap = new HashMap<>();

        // fill the threadMap with thread data
        for (Thread thread : outputData.getThreads()) {
            threadMap.put(thread.getThreadID(), thread.getName()); // placeholder for any thread names
        }

        threadsState.setThreadHash(threadMap);
        threadsViewModel.firePropertyChanged("threads-updated");
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

        // 5. get the chatState out of the appropriate view model
        ChatState chatState = chatViewModel.getState();
        ThreadsState threadsState = threadsViewModel.getState();
        // set the current threadname and threadid to current thread
        chatState.setCurrentThreadID(threadID);
        chatState.setCurrentThreadName(threadsState.getThreadHash().get(threadID));
        // 7. set the state in the chatViewModel to the updated state
        chatViewModel.setState(chatState);
        // 8. firePropertyChanged so that the View that is listening is updated.
        chatViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the chatView.
        viewManagerModel.setState(chatViewModel.getViewName());

        System.out.println(viewManagerModel.getState());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToAddThreadView()
    {
        // This code tells the View Manager to switch to the AddThreads view.
        viewManagerModel.setState(addThreadViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
