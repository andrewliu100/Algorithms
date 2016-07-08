package geekspearls.graph.entity;

import java.util.Set;

/**
 * Adjacency matrix representation of graph
 *
 * Created by aliu on 7/07/16.
 */
public class AdjMatrixGraph implements Graph {

    private Set<Vertex> V; // the vertex set
    private int E; // the number of edges
    private boolean[][] adj; // the adjacency matrix
    private GraphType type; // the graph type. Directed or undirected

    public AdjMatrixGraph(Set<Vertex> V, GraphType type) {
        this.V = V;
        int v = V.size();
        this.adj = new boolean[v][v];
        this.E = 0;
        this.type = type;
    }

    @Override
    public void addEdge(Vertex v, Vertex w) {
        if (!adj[v.getValue()][w.getValue()]) {
            E++;
        }
        if (type == GraphType.DIRECTED) {
            adj[v.getValue()][w.getValue()] = true;
        } else if (type == GraphType.UNDIRECTED) {
            adj[v.getValue()][w.getValue()] = true;
            adj[w.getValue()][v.getValue()] = true;
        }
    }

    @Override
    public void print() {
        System.out.println("========== GRAPH - " + type + "============");
        for (int i = 1; i < adj.length; i++) {
            for (int j = 1; j < adj[i].length; j++) {
                System.out.print(adj[i][j] ? "1, " : "0, ");
            }
            System.out.println();
        }
    }
}