package geekspearls.atlas;

import java.util.ArrayList;
import java.util.List;

public class GroupService {

    private List<Group> unprocessed = new ArrayList<>();
    private List<Group> inProcess = new ArrayList<>();

    public Group createGroup(Integer groupId) {
        return new Group(groupId);
    }

    public void addGroupToGroup(Group parent, Group child) {
        parent.addGroup(child);
    }

    public void addUserToGroup(User user, Group group) {
        try {
            Thread.sleep(3000);
            group.addUser(user);
        } catch (InterruptedException ex) {
            // do nothing
        }
    }

    public void deleteUserFromGroup(User user, Group group) {
        group.deleteUser(user);
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
            for (Group cg : group.getChildGroups()) {
                if (hasCycle(cg)) {
                    return true;
                }
            }
            inProcess.remove(group);
        }
        return false;
    }

}
