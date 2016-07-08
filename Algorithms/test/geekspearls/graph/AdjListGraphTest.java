package geekspearls.graph;

import geekspearls.graph.entity.GraphType;
import geekspearls.graph.entity.Vertex;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Build one undirected graph and one directed graph in adjacency list. Then print them out.
 *
 * Created by aliu on 8/07/16.
 */
public class AdjListGraphTest {

    /**
     * 1->2->5
     * 2->1->5->3->4
     * 3->2->4
     * 4->2->5->3
     * 5->4->1->2
     */
    @Test
    public void buildUndiretedGraph() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(0)); // dummy vertex
        for (int x = 1; x <= 5; x++) {
            vertices.add(new Vertex(x));
        }
        Graph undirectedAdjList = new AdjListGraph(new HashSet<>(vertices), GraphType.UNDIRECTED);
        undirectedAdjList.addEdge(vertices.get(1), vertices.get(2));
        undirectedAdjList.addEdge(vertices.get(1), vertices.get(5));
        undirectedAdjList.addEdge(vertices.get(2), vertices.get(5));
        undirectedAdjList.addEdge(vertices.get(2), vertices.get(3));
        undirectedAdjList.addEdge(vertices.get(2), vertices.get(4));
        undirectedAdjList.addEdge(vertices.get(3), vertices.get(4));
        undirectedAdjList.addEdge(vertices.get(4), vertices.get(5));
        undirectedAdjList.print();
    }

    /**
     * 1->2->4
     * 2->5
     * 3->6->5
     * 4->2
     * 5->4
     * 6->6
     */
    @Test
    public void buildDiretedGraph() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(0)); // dummy vertex
        for (int x = 1; x <= 6; x++) {
            vertices.add(new Vertex(x));
        }
        Graph directedAdjList = new AdjListGraph(new HashSet<>(vertices), GraphType.DIRECTED);
        directedAdjList.addEdge(vertices.get(1), vertices.get(2));
        directedAdjList.addEdge(vertices.get(1), vertices.get(4));
        directedAdjList.addEdge(vertices.get(2), vertices.get(5));
        directedAdjList.addEdge(vertices.get(3), vertices.get(6));
        directedAdjList.addEdge(vertices.get(3), vertices.get(5));
        directedAdjList.addEdge(vertices.get(4), vertices.get(2));
        directedAdjList.addEdge(vertices.get(5), vertices.get(4));
        directedAdjList.addEdge(vertices.get(6), vertices.get(6));
        directedAdjList.print();
    }

}
