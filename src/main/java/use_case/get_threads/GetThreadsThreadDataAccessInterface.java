package use_case.get_threads;

import entity.Thread;

import java.util.List;

public interface GetThreadsThreadDataAccessInterface
{

    List<Thread> getThreadsByUsername(String username);

}
