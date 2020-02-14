package geekspearls.atlas;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MembershipServiceTest {

    private MembershipService membershipService;
    private GroupService groupService;
    private UserService userService;

    @BeforeClass
    public void init() {
        membershipService = Services.createMembershipService();
        groupService = Services.createGroupService();
        userService = Services.createUserService();
    }

    // Q1
    @Test
    public void testDeleteUser() {
        // given
        Group group = new Group("group");
        groupService.create(group);
        User user = new User("user");
        userService.createUser(user);
        membershipService.addUserToGroup(group, user);

        // when
        membershipService.removeUserFromGroup(group, user);

        // then
        Assert.assertTrue(membershipService.hasUser(group, user));
    }

    // Q2
    @Test
    public void testCreateDeleteGroup() {
        // given
        Group group = new Group("group");
        groupService.create(group);
        User user = new User("user");
        userService.createUser(user);
        membershipService.addUserToGroup(group, user);

        // when
        groupService.remove(group);

        // then
        Assert.assertTrue(membershipService.getUsers(group).isEmpty());
    }

    // Q3
    @Test
    public void testAddUser() {
        // given
        Group staff = new Group("staff");
        groupService.create(staff);
        User alex = new User("alex");
        userService.createUser(alex);
        membershipService.addUserToGroup(staff, alex);

        // when
        Assert.assertTrue(membershipService.hasUser(staff, new User("alex")));
    }

    /////// Normally not time for the questions below

    @Test
    public void testMultiThreadsAddingUser() throws InterruptedException {
        // given
        Group testGroup = new Group("1");
        User testUser1 = new User("1");
        User testUser2 = new User("2");

        // when
        Thread thread1 = new Thread(() -> membershipService.addUserToGroup(testGroup, testUser1));
        Thread thread2 = new Thread(() -> membershipService.addUserToGroup(testGroup, testUser2));
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        // then
        Assert.assertEquals(membershipService.getUsers(testGroup).size(), 2);
    }

    @Test
    public void testMultiThreadAddingDeletingUser() throws InterruptedException {
        // given
        Group testGroup = new Group("1");
        User testUser = new User("1");
        membershipService.addUserToGroup(testGroup, testUser);

        // when
        Thread addThread = new Thread(() -> membershipService.addUserToGroup(testGroup, testUser));
        Thread deleteThread = new Thread(() -> membershipService.removeUserFromGroup(testGroup, testUser));
        deleteThread.start();
        addThread.start();

        addThread.join();
        deleteThread.join();

        // then
        Assert.assertEquals(membershipService.getUsers(testGroup).size(), 1);
    }

    @Test
    public void testExecutorServiceAddingDeletingUser() throws InterruptedException {
        // given
        Group testGroup = new Group("1");
        User testUser = new User("1");
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // when
        List<Callable<Void>> tasks = new ArrayList<>();
        tasks.add(() -> {
            membershipService.addUserToGroup(testGroup, testUser);
            return null;
        });
        tasks.add(() -> {
            membershipService.removeUserFromGroup(testGroup, testUser);
            return null;
        });

        List<Future<Void>> futures = executor.invokeAll(tasks);
        try {
            for (Future<Void> future : futures) {
                future.get();
            }
        } catch (ExecutionException ex) {
            // do nothing
        }

        executor.shutdown();

        // then
        Assert.assertEquals(membershipService.getUsers(testGroup).size(), 0);
    }
}
