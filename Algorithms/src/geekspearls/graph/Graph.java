package geekspearls.graph;

import geekspearls.graph.entity.Vertex;

/**
 * Created by aliu on 7/07/16.
 */
public interface Graph {

    public void addEdge(Vertex v, Vertex w);

    /**
     * Print the graph structure.
     */
    public void print();
}
