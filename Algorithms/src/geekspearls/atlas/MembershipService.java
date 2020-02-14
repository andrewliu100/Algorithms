package geekspearls.atlas;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class MembershipService {

    Logger log = Logger.getLogger(MembershipService.class.getName());

    private Map<Group, Set<User>> users = new HashMap<>();
    private Map<Group, Set<Group>> groups = new HashMap<>();

    public MembershipService() {
    }

    public void addUserToGroup(Group group, User user) {
        if (!users.containsKey(group)) {
            users.put(group, new HashSet<>());
        }
        Set<User> usersInGroup = users.get(group);
        usersInGroup.add(user);
    }

    public void addGroupToGroup(Group parent, Group child) {
        if (!groups.containsKey(parent)) {
            groups.put(parent, new HashSet<>());
        }
        Set<Group> groupsInGroup = groups.get(parent);
        groupsInGroup.add(child);
    }

    public void removeUserFromGroup(Group group, User user) {
        // Q1 bug
        Set<User> userSet = users.get(group); // bug here, userSet is NULL. Fix is to use getOrDefault()
        log.info("removing user from group " + group.toString() + " " + userSet.toString()); // NPE here
        userSet.remove(user); // NPE here
    }

    public boolean hasUser(Group group, User user) {
        if (!users.containsKey(group)) {
            return false;
        }
        return users.get(group).contains(user);
    }

    public Set<Group> getChildGroups(Group group) {
        return groups.get(group);
    }

    public Set<User> getUsers(Group group) {
        return users.get(group);
    }

}
