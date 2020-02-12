package geekspearls.atlas;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Group {

    private Integer groupId;

    private Set<User> users;

    private Set<Group> childGroups;

    public Group(Integer groupId) {
        this.groupId = groupId;
        this.users = new HashSet<>();
        this.childGroups = new HashSet<>();
    }

    public void addGroup(Group group) {
        childGroups.add(group);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void deleteUser(User userToDelete) {
        this.users.remove(userToDelete);
    }

    public int getUserCount() {
        return users.size();
    }

    public Set<Group> getChildGroups() {
        return childGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Group group = (Group) o;
        return groupId.equals(group.groupId) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }
}
