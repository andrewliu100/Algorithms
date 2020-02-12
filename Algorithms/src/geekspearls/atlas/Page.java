package geekspearls.atlas;

import java.util.ArrayList;
import java.util.List;

public class Page {
    int id;
    List<Page> childPages;

    public Page(int id) {
        this.id = id;
        childPages = new ArrayList<>();
    }
}
