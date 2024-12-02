package Entity;

import entity.ThreadFactory;
import entity.Thread;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadEntityTest
{
    private ThreadFactory threadFactory = new ThreadFactory();

    @Test
    void testToString()
    {
        Thread thread = threadFactory.create("benj", "Benj, Ali");
        assertEquals(thread.toString(), "Thread{" +
                "threadID=" + thread.getThreadID() +
                ", name='" + thread.getName() + '\'' +
                ", usernameList=" + thread.getUsernameList() +
                ", messageList=" + thread.getMessageList() +
                '}');

    }

    @Test
    void testSetName()
    {
        Thread thread = threadFactory.create("benj", "Benj, Ali");
        thread.setName("Ali");
        assertEquals(thread.getName(), "Ali");
    }

    @Test
    void testAltConstructor()
    {
        Thread thread = new Thread(1L, "Ben");
        assertEquals(thread.getThreadID(), 1L);
        assertEquals(thread.getName(), "Ben");
    }

    @Test
    void testConvertCommaSeperatedToListBranches()
    {
       Thread thread = threadFactory.create("Benj", null);
       Thread thread2 = threadFactory.create("Benj", "");
    }



}
