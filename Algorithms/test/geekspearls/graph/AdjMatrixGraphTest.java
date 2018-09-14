package geekspearls.graph;

import geekspearls.graph.entity.GraphType;
import geekspearls.graph.entity.Vertex;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Build one undirected graph and one directed graph in adjacency matrix. Then print them out.
 *
 * Created by aliu on 7/07/16.
 */
public class AdjMatrixGraphTest {


    /**
     *  |1 2 3 4 5
     * -----------
     * 1|0 1 0 0 1
     * 2|1 0 1 1 1
     * 3|0 1 0 1 0
     * 4|0 1 1 0 1
     * 5|1 1 0 1 0
     */
    @Test
    public void buildUndirectedGraph() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(0)); // dummy vertex
        for (int x = 1; x <= 5; x++) {
            vertices.add(new Vertex(x));
        }
        Graph undirectedAdjMatrix = new AdjMatrixGraph(new HashSet<>(vertices), GraphType.UNDIRECTED);
        undirectedAdjMatrix.addEdge(vertices.get(1), vertices.get(2));
        undirectedAdjMatrix.addEdge(vertices.get(1), vertices.get(5));
        undirectedAdjMatrix.addEdge(vertices.get(2), vertices.get(3));
        undirectedAdjMatrix.addEdge(vertices.get(2), vertices.get(4));
        undirectedAdjMatrix.addEdge(vertices.get(2), vertices.get(5));
        undirectedAdjMatrix.addEdge(vertices.get(3), vertices.get(4));
        undirectedAdjMatrix.addEdge(vertices.get(4), vertices.get(5));
        undirectedAdjMatrix.print();
    }

    /**
     *  |1 2 3 4 5 6
     * -------------
     * 1|0 1 0 1 0 0
     * 2|0 0 0 0 1 0
     * 3|0 0 0 0 1 1
     * 4|0 1 0 0 0 0
     * 5|0 0 0 1 0 0
     * 6|0 0 0 0 0 1
     */
    @Test
    public void buildDirectedGraph() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(0)); // dummy vertex
        for (int x = 1; x <= 6; x++) {
            vertices.add(new Vertex(x));
        }
        Graph directedAdjMatrix = new AdjMatrixGraph(new HashSet<>(vertices), GraphType.DIRECTED);
        directedAdjMatrix.addEdge(vertices.get(1), vertices.get(2));
        directedAdjMatrix.addEdge(vertices.get(1), vertices.get(4));
        directedAdjMatrix.addEdge(vertices.get(2), vertices.get(5));
        directedAdjMatrix.addEdge(vertices.get(3), vertices.get(5));
        directedAdjMatrix.addEdge(vertices.get(3), vertices.get(6));
        directedAdjMatrix.addEdge(vertices.get(4), vertices.get(2));
        directedAdjMatrix.addEdge(vertices.get(5), vertices.get(4));
        directedAdjMatrix.addEdge(vertices.get(6), vertices.get(6));
        directedAdjMatrix.print();
    }

    @Test
    public void testBFS() {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex(0)); // dummy vertex
        for (int x = 1; x <= 6; x++) {
            vertices.add(new Vertex(x));
        }
        Graph directedAdjMatrix = new AdjMatrixGraph(new HashSet<>(vertices), GraphType.DIRECTED);
        directedAdjMatrix.addEdge(vertices.get(1), vertices.get(2));
        directedAdjMatrix.addEdge(vertices.get(1), vertices.get(4));
        directedAdjMatrix.addEdge(vertices.get(2), vertices.get(5));
        directedAdjMatrix.addEdge(vertices.get(3), vertices.get(5));
        directedAdjMatrix.addEdge(vertices.get(3), vertices.get(6));
        directedAdjMatrix.addEdge(vertices.get(4), vertices.get(2));
        directedAdjMatrix.addEdge(vertices.get(5), vertices.get(4));
        directedAdjMatrix.addEdge(vertices.get(6), vertices.get(6));
        directedAdjMatrix.bfs(vertices.get(1));
    }

}
