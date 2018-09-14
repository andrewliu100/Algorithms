package geekspearls.graph.entity;

/**
 * Created by aliu on 7/07/16.
 */
public class Vertex {

    private int value;

    /**
     * Flag if the vertex is already visited
     */
    private boolean visited;

    public Vertex(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
