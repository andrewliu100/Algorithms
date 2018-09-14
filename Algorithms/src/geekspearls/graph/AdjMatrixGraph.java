package geekspearls.graph;

import geekspearls.graph.entity.GraphType;
import geekspearls.graph.entity.Vertex;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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

    @Override
    public void bfs(Vertex v) {
        Queue<Vertex> queue = new LinkedList<>();
        queue.offer(v);
        v.setVisited(true);
        while (!queue.isEmpty()) {
            Vertex ver = queue.poll();
            System.out.print(ver.getValue() + ", ");
            for (Vertex neighbour : neighbours(ver)) {
                if (!neighbour.isVisited()) {
                    queue.offer(neighbour);
                    neighbour.setVisited(true);
                }
            }
        }
    }

    @Override
    public void dfs(Vertex vertex, boolean iterative) {
        throw new NotImplementedException();
    }

    private Set<Vertex> neighbours(Vertex v) {
        Set<Vertex> neighbours = new HashSet<>();
        for (int i = 1; i < adj.length; i++) {
            if (adj[v.getValue()][i]) {
                neighbours.add(getVertex(i));
            }
        }
        return neighbours;
    }

    private Vertex getVertex(int value) {
        for (Vertex v: V) {
            if (v.getValue() == value) {
                return v;
            }
        }
        return null;
    }
}
