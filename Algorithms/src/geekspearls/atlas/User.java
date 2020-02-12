package geekspearls.atlas;

import java.util.List;
import java.util.Objects;

public class User {

    int id;

    List<Page> accessiblePages;

    public User(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
