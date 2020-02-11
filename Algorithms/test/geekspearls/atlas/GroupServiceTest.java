package geekspearls.atlas;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GroupServiceTest {

    private GroupService groupService;

    @BeforeClass
    public void before() {
        groupService = new GroupService();
    }

    @Test
    public void testDeleteUserBelongsToGroup() {
        // given
        Group testGroup = groupService.createGroup();
        User testUser = new User(1);
        groupService.addUserToGroup(testUser, testGroup);

        // when
        groupService.deleteUserFromGroup(testUser, testGroup);

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
        Thread thread1 = new Thread(() -> groupService.addUserToGroup(testUser1, testGroup));
        Thread thread2 = new Thread(() -> groupService.addUserToGroup(testUser2, testGroup));
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
        Thread addThread = new Thread(() -> groupService.addUserToGroup(testUser, testGroup));
        Thread deleteThread = new Thread(() -> groupService.deleteUserFromGroup(testUser, testGroup));
        addThread.start();
        deleteThread.start();

        addThread.join();
        deleteThread.join();

        // then
        Assert.assertEquals(testGroup.getUserCount(), 0);
    }

    @Test
    public void testHasCyclicGroup() {
        // given
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        groupService.addGroupToGroup(group1, group2);
        groupService.addGroupToGroup(group2, group3);
        groupService.addGroupToGroup(group3, group2);

        // when
        List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        boolean hasCyclic = groupService.hasCyclicGroup(groups);

        // then
        Assert.assertTrue(hasCyclic);
    }

    @Test
    public void testNotHaveCyclicGroup() {
        // given
        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        groupService.addGroupToGroup(group1, group2);
        groupService.addGroupToGroup(group2, group3);

        // when
        List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        boolean hasCyclic = groupService.hasCyclicGroup(groups);

        // then
        Assert.assertFalse(hasCyclic);
    }
}
