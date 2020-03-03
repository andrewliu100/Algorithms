package geekspearls.amz;

import java.util.ArrayList;
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
    public List<Integer> findArticulationVertices(int numNodes, int numEdges, List<Edge> edges) {
        List<Integer> articulationPoints = new ArrayList<>();

        List<Integer> verticesRemains = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            verticesRemains.add(i);
        }

        for (int i = 0; i < numNodes; i++) {
            List<Edge> edgesRemains = new ArrayList<>();
            for (Edge e : edges) {
                if (e.src != i && e.dest != i) {
                    edgesRemains.add(e);
                }
            }
            verticesRemains.remove(i);

        }

        return articulationPoints;
    }

}
