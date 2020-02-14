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

public class GroupServiceTest {

    private GroupService groupService;
    private MembershipService membershipService;

    @BeforeClass
    public void before() {
        groupService = Services.createGroupService();
        membershipService = Services.createMembershipService();
    }

    @Test
    public void testHasCyclicGroup() {
        // given
        Group group1 = new Group("1");
        Group group2 = new Group("2");
        Group group3 = new Group("3");
        membershipService.addGroupToGroup(group1, group2);
        membershipService.addGroupToGroup(group2, group3);
        membershipService.addGroupToGroup(group3, group2);

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
        Group group1 = new Group("1");
        Group group2 = new Group("2");
        Group group3 = new Group("3");
        membershipService.addGroupToGroup(group1, group2);
        membershipService.addGroupToGroup(group2, group3);

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
