package geekspearls.atlas;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Group {

    private Set<User> users;

    private Set<Group> childGroups;

    public Group() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Group group = (Group) o;
        return users.equals(group.users) && childGroups.equals(group.childGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users, childGroups);
    }
}
