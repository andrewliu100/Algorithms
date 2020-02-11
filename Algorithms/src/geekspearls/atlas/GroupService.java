package geekspearls.atlas;

import java.util.ArrayList;
import java.util.List;

public class GroupService {

    private List<Group> unprocessed = new ArrayList<>();
    private List<Group> inProcess = new ArrayList<>();
    private List<Group> completed = new ArrayList<>();

    public Group createGroup() {
        return new Group();
    }

    public void addGroupToGroup(Group parent, Group child) {
        parent.addGroup(child);
    }

    public void addUserToGroup(User user, Group group) {
        group.addUser(user);
    }

    public void deleteUserFromGroup(User user, Group group) {
        group.deleteUser(user);
    }

    public boolean hasCyclicGroup(List<Group> groups) {
        return false;
    }

    private boolean hasCycle(Group group) {
        return false;
    }

}