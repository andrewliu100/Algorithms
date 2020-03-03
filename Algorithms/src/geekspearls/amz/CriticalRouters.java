package geekspearls.amz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given an undirected connected graph. An articulation point (or cut vertex) is defined as a vertex which, when removed along with associated edges, makes the graph disconnected (or more precisely, increases the number of connected components in the graph). The task is to find all articulation points in the given graph.
 *
 * Input:
 * The input to the function/method consists of three arguments:
 *
 * numNodes, an integer representing the number of nodes in the graph.
 * numEdges, an integer representing the number of edges in the graph.
 * edges, the list of pair of integers - A, B representing an edge between the nodes A and B.
 * Output:
 * Return a list of integers representing the critical nodes.
 *
 * Example:
 *
 * Input: numNodes = 7, numEdges = 7, edges = [[0, 1], [0, 2], [1, 3], [2, 3], [2, 5], [5, 6], [3, 4]]
 * Output: [2, 3, 5]
 */
public class CriticalRouters {

    static class Edge {
        int src;
        int dest;

        Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    static class Graph {
        int vertices;
        LinkedList<Integer> edges[];

        Graph(int vertices) {
            this.vertices = vertices;
            edges = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                edges[i] = new LinkedList<>();
            }
        }

        void addEdge(int src, int dest) {
            edges[src].addFirst(dest);
            edges[dest].add(src);
        }

        boolean isConnectedExcludeVertex(int exclude) {
            List<Integer> visited = new ArrayList<>();
            int start = 0;
            for (int i = 0; i < vertices; i++) {
                if (i != exclude) {
                    start = i;
                    break;
                }
            }
            dfs(start, visited);
            return visited.size() == vertices - 1;
        }

        void dfs(int start, List<Integer> visited) {
            visited.add(start);
            for (Integer dest : edges[start]) {
                if (!visited.contains(dest)) {
                    dfs(dest, visited);
                }
            }
        }
    }

    /**
     * Brute force: O(V*(V+E))
     * For each vertices, do the following:
     * 1. Remove the vertex
     * 2. Check if the graph connected
     *
     * Tarjan algorithm O(V+E)
     * https://en.wikipedia.org/wiki/Biconnected_component
     * https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/ArticulationPoint.java
     *
     * This method is brute force
     */
    public static List<Integer> findArticulationVertices(int numNodes, int numEdges, List<Edge> edges) {
        List<Integer> articulationPoints = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            Graph graph = new Graph(numNodes);
            for (Edge e : edges) {
                if (e.src != i && e.dest != i) {
                    graph.addEdge(e.src, e.dest);
                }
            }

            if (!graph.isConnectedExcludeVertex(i)) {
                articulationPoints.add(i);
            }
        }

        return articulationPoints;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(5, 6));
        edges.add(new Edge(3, 4));
        List<Integer> articulationPoints = findArticulationVertices(7, 7, edges);
        System.out.println(articulationPoints);
    }

}
