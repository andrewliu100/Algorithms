package geekspearls.amz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network
 * where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server
 * directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * <p>
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 */
public class CriticalConnections {

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

        public boolean isConnected() {
            List<Integer> visited = new ArrayList<>();
            dfs(0, visited);
            return visited.size() == vertices;
        }
    }

    static class Connection {
        int v1;
        int v2;

        Connection(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        public boolean isSameConnection(Connection conn) {
            return (this.v1 == conn.v1 && this.v2 == conn.v2) ||
                    (this.v1 == conn.v2 && this.v2 == conn.v1);
        }
    }

    /**
     * Brute force.
     * 1) For every edge (u, v), do following
     * …..a) Remove (u, v) from graph
     * ..…b) See if the graph remains connected (We can either use BFS or DFS)
     * …..c) Add (u, v) back to the graph.
     * <p>
     * Time: O(E*(V+E))
     */
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> critical = new ArrayList<>();
        // exclude every edge and check if the graph is connected
        for (List<Integer> exclude : connections) {
            Graph graph = new Graph(n);
            Connection excludedConn = new Connection(exclude.get(0), exclude.get(1));
            for (List<Integer> edge : connections) {
                Connection conn = new Connection(edge.get(0), edge.get(1));
                if (!conn.isSameConnection(excludedConn)) {
                    graph.addEdge(edge.get(0), edge.get(1));
                }
            }
            if (!graph.isConnected()) {
                critical.add(exclude);
            }
        }
        return critical;
    }
}
