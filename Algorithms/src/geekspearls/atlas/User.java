package geekspearls.atlas;

import java.util.List;
import java.util.Objects;

public class User {

    private String name;

    List<Page> accessiblePages;

    public User(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        User user = (User) o;
//        return Objects.equals(name, user.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
}
