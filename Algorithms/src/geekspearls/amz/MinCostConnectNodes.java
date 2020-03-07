package geekspearls.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an undirected graph with n nodes labeled 1..n. Some of the nodes are already connected. The i-th edge connects
 * nodes edges[i][0] and edges[i][1] together. Your task is to augment this set of edges with additional edges to
 * connect all the nodes. Find the minimum cost to add new edges between the nodes such that all the nodes are
 * accessible from each other.
 *
 * Input:
 *
 * n, an int representing the total number of nodes.
 * edges, a list of integer pair representing the nodes already connected by an edge.
 * newEdges, a list where each element is a triplet representing the pair of nodes between which an edge can be added
 * and the cost of addition, respectively (e.g. [1, 2, 5] means to add an edge between node 1 and 2, the cost would be 5).
 * Example 1:
 *
 * Input: n = 6, edges = [[1, 4], [4, 5], [2, 3]], newEdges = [[1, 2, 5], [1, 3, 10], [1, 6, 2], [5, 6, 5]]
 * Output: 7
 * Explanation:
 * There are 3 connected components [1, 4, 5], [2, 3] and [6].
 * We can connect these components into a single component by connecting node 1 to node 2 and node 1 to node 6 at
 * a minimum cost of 5 + 2 = 7.
 */
public class MinCostConnectNodes {

    static class Graph {
        int vertices;
        List<Integer>[] edges;

        Graph(int vertices) {
            this.vertices = vertices;
            this.edges = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                this.edges[i] = new LinkedList<>();
            }
        }

        public void addEdge(int src, int dest) {
            this.edges[src].add(dest);
            this.edges[dest].add(src);
        }

        public void dfs(int vertex, List<Integer> visited) {
            visited.add(vertex);
            for (Integer dest : edges[vertex]) {
                if (!visited.contains(dest)) {
                    dfs(dest, visited);
                }
            }
        }

        public boolean isReachable(int v1, int v2) {
            List<Integer> visited = new ArrayList<>();
            dfs(v1, visited);
            return visited.contains(v2);
        }

        public boolean isConnected() {
            List<Integer> visited = new ArrayList<>();
            dfs(0, visited);
            return visited.size() == vertices;
        }
    }

    static class EdgeWithCost implements Comparable<EdgeWithCost> {
        int src;
        int dest;
        int cost;

        EdgeWithCost(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        public int compareTo(EdgeWithCost that) {
            return Integer.compare(this.cost, that.cost);
        }
    }

    /**
     * Greedy + Graph DFS.
     *
     * 1. Pick the min cost edge from new edges
     * 2. Add it to the graph if the two vertices are not connected yet
     * 3. DFS to check if connected
     * Repeat above until the graph is connected
     */
    public static int minCost(int vertices, List<List<Integer>> existEdges, List<List<Integer>> edgeCosts) {
        // Create the graph
        Graph graph = new Graph(vertices);
        for (List<Integer> edge : existEdges) {
            graph.addEdge(edge.get(0) - 1, edge.get(1) - 1);
            graph.addEdge(edge.get(1) - 1, edge.get(0) - 1);
        }

        // Use min heap to pick up the min cost edge
        PriorityQueue<EdgeWithCost> minHeap = new PriorityQueue<>();
        for (List<Integer> newEdge : edgeCosts) {
            minHeap.add(new EdgeWithCost(newEdge.get(0) - 1, newEdge.get(1) - 1, newEdge.get(2)));
        }

        int cost = 0;
        while (!graph.isConnected() && !minHeap.isEmpty()) {
            EdgeWithCost edgeToAdd = minHeap.poll();
            // Use the edge only if the two vertices are not connected
            if (!graph.isReachable(edgeToAdd.src, edgeToAdd.dest)) {
                cost += edgeToAdd.cost;
                graph.addEdge(edgeToAdd.src, edgeToAdd.dest);
                graph.addEdge(edgeToAdd.dest, edgeToAdd.src);
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        int n = 6;
        List<List<Integer>> edges = Arrays.asList(Arrays.asList(1, 4), Arrays.asList(4, 5), Arrays.asList(2, 3));
        List<List<Integer>> newEdges = Arrays.asList(Arrays.asList(1, 2, 5),
                Arrays.asList(1, 3, 10), Arrays.asList(1, 6, 2), Arrays.asList(5, 6, 5));
        System.out.println(minCost(n, edges, newEdges));
    }
}
