package geekspearls.atlas;

import java.util.Objects;

public class Group {

    private String name;

    private Object deleteLock = new Object();

    public Group(String name) {
        this.name = name;
    }

    // Atlassian's code doesn't have equals() and hashCode(). The 3rd question is to fix a bug of adding equals() and
    // hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Group group = (Group) o;
        return name.equals(group.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
