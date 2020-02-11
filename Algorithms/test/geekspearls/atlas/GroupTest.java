package geekspearls.atlas;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupTest {

    @Test
    public void testDeleteUserBelongsToGroup() {
        // given
        Group testGroup = new Group();
        User testUser = new User(1);
        testGroup.addUser(testUser);

        // when
        testGroup.deleteUser(testUser);

        // then
        Assert.assertEquals(testGroup.getUserCount(), 0);
    }

    @Test
    public void testDeleteUserNotBelongsToGroup() {
        // given
        Group testGroup = new Group();
        User testUser1 = new User(1);
        testGroup.addUser(testUser1);
        User testUser2 = new User(2);

        // when
        testGroup.deleteUser(testUser2);

        // then
        Assert.assertEquals(testGroup.getUserCount(), 1);
    }

    @Test
    public void testMultiThreadsAddingUser() throws InterruptedException {
        // given
        Group testGroup = new Group();
        User testUser1 = new User(1);
        User testUser2 = new User(2);

        // when
        Thread thread1 = new Thread(new AddUserTask(testGroup, testUser1));
        Thread thread2 = new Thread(new AddUserTask(testGroup, testUser2));
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // then
        Assert.assertEquals(testGroup.getUserCount(), 2);
    }

    @Test
    public void testMultiThreadAddingDeletingUser() throws InterruptedException {
        // given
        Group testGroup = new Group();
        User testUser = new User(1);

        // when
        Thread addThread = new Thread(new AddUserTask(testGroup, testUser));
        Thread deleteThread = new Thread(new DeleteUserTask(testGroup, testUser));
        addThread.start();
        deleteThread.start();

        addThread.join();
        deleteThread.join();

        // then
        Assert.assertEquals(testGroup.getUserCount(), 0);
    }
}
