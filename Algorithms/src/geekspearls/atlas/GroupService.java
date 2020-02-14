package geekspearls.atlas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupService {

    private List<Group> unprocessed = new ArrayList<>();
    private List<Group> inProcess = new ArrayList<>();

    private Set<Group> groups = new HashSet<>();

    private MembershipService membershipService;

    public GroupService() {
        membershipService = Services.createMembershipService();
    }

    public void create(Group group) {
        groups.add(group);
    }

    public void remove(Group group) {
        groups.remove(group);
        // Q2 bug
        // removing group doesn't clear users set
        // fix is membershipService.getUsers(group).clear();
    }

    public boolean hasCyclicGroup(List<Group> groups) {
        unprocessed.addAll(groups);
        inProcess.clear();
        return groups.stream().anyMatch(
          g -> unprocessed.contains(g) && hasCycle(g)
        );
    }

    private boolean hasCycle(Group group) {
        if (inProcess.contains(group)) {
            return true;
        }
        if (unprocessed.contains(group)) {
            inProcess.add(group); // set to inprocess
            unprocessed.remove(group);
            for (Group cg : membershipService.getChildGroups(group)) {
                if (hasCycle(cg)) {
                    return true;
                }
            }
            inProcess.remove(group);
        }
        return false;
    }

}
