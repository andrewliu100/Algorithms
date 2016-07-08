package geekspearls.graph;

import geekspearls.graph.entity.GraphType;
import geekspearls.graph.entity.Vertex;

import java.util.LinkedList;
import java.util.Set;

/**
 * Adjacency list representation of graph.
 *
 * Created by aliu on 8/07/16.
 */
public class AdjListGraph implements Graph {

    private Set<Vertex> V; // the vertex set
    private int E; // the number of edges
    private LinkedList<Vertex>[] adj; // the adjacency list
    private GraphType type; // the graph type. Directed of Undirected

    public AdjListGraph(Set<Vertex> V, GraphType type) {
        this.V = V;
        int v = V.size();
        this.adj = new LinkedList[v];
        for (Vertex ver : V) {
            this.adj[ver.getValue()] = new LinkedList<>();
        }
        this.E = 0;
        this.type = type;
    }

    @Override
    public void addEdge(Vertex v, Vertex w) {
        if (type == GraphType.UNDIRECTED) {
            if (!adj[v.getValue()].contains(w)) {
                adj[v.getValue()].add(w);
            }
            if (!adj[w.getValue()].contains(v)) {
                adj[w.getValue()].add(v);
            }
        } else if (type == GraphType.DIRECTED) {
            if (!adj[v.getValue()].contains(w)) {
                adj[v.getValue()].add(w);
            }
        }
    }

    @Override
    public void print() {
        System.out.println("========== GRAPH - " + type + "============");
        for (int i = 1; i < adj.length; i++) {
            System.out.print(i + "->");
            LinkedList<Vertex> adjlist = adj[i];
            for (Vertex v : adjlist) {
                System.out.print(v.getValue() + "->");
            }
            System.out.println();
        }
    }
}
