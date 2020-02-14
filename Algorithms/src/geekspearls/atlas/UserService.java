package geekspearls.atlas;

import java.util.HashSet;
import java.util.Set;

public class UserService {

    private Set<User> users = new HashSet<>();

    public UserService() {
    }

    public void createUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
