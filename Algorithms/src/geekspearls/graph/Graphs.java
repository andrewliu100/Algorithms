package geekspearls.graph;

import geekspearls.graph.entity.AdjMatrixGraph;
import geekspearls.graph.entity.Graph;
import geekspearls.graph.entity.GraphType;
import geekspearls.graph.entity.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by aliu on 7/07/16.
 */
public class Graphs {

    /**
     *  |1 2 3 4 5
     * -----------
     * 1|0 1 0 0 1
     * 2|1 0 1 1 1
     * 3|0 1 0 1 0
     * 4|0 1 1 0 1
     * 5|1 1 0 1 0
     */
    private static Graph undirectedAdjMatrix;

    public static Graph buildUndirectedGraph() {
        List<Vertex> vertices = new ArrayList<>();
        for (int x = 1; x <= 5; x++) {
            vertices.add(new Vertex(x));
        }
        undirectedAdjMatrix = new AdjMatrixGraph(new HashSet<>(vertices), GraphType.UNDIRECTED);
        undirectedAdjMatrix.addEdge(vertices.get(1), vertices.get(2));
        undirectedAdjMatrix.addEdge(vertices.get(1), vertices.get(5));
        undirectedAdjMatrix.addEdge(vertices.get(2), vertices.get(3));
        undirectedAdjMatrix.addEdge(vertices.get(2), vertices.get(4));
        undirectedAdjMatrix.addEdge(vertices.get(2), vertices.get(5));
        undirectedAdjMatrix.addEdge(vertices.get(3), vertices.get(4));
        undirectedAdjMatrix.addEdge(vertices.get(4), vertices.get(5));
        return undirectedAdjMatrix;
    }


    public static void main(String[] args) {
        Graph graph1 = Graphs.buildUndirectedGraph();
        graph1.print();
    }

}
