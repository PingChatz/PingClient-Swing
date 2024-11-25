package use_case.get_threads;

import java.util.List;

public interface GetThreadsThreadDataAccessInterface {
    List<Long> getUserThreadIDs(Long userID); // get all thread ids for user
}
