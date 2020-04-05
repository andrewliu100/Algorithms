package geekspearls.amz.coding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLadderII {

    class Graph {
        List<String> vertices;
        Map<String, List<String>> edges;

        Graph(List<String> vertices) {
            this.vertices = vertices;
            edges = new HashMap<>();
        }

        void addEdge(String v1, String v2) {
            List<String> dests1 = edges.getOrDefault(v1, new LinkedList<>());
            if (!dests1.contains(v2)) {
                dests1.add(v2);
                edges.put(v1, dests1);
            }

            List<String> dests2 = edges.getOrDefault(v2, new LinkedList<>());
            if (!dests2.contains(v1)) {
                dests2.add(v1);
                edges.put(v2, dests2);
            }
        }
    }

    class Vertex {
        String word;
        Vertex pre;
        Vertex(String word, Vertex pre) {
            this.word = word;
            this.pre = pre;
        }
    }

    public List<List<String>> findLadders(String begin, String end, List<String> dict) {
        // create graph
        Graph graph = buildGraph(begin, dict);

        // bfs
        List<Vertex> res = new ArrayList<>();
        Deque<Vertex> queue = new ArrayDeque<>();
        List<String> visited = new ArrayList<>();
        queue.addLast(new Vertex(begin, null));
        while (!queue.isEmpty()) {
            Vertex curr = queue.removeFirst();
            visited.add(curr.word);

            if (curr.word.equals(end)) {
                res.add(curr);
            }
            for (String next : graph.edges.getOrDefault(curr.word, new LinkedList<>())) {
                if (!visited.contains(next)) {
                    queue.addLast(new Vertex(next, curr));
                }
            }
        }

        // collect result
        List<List<String>> ladders = new ArrayList<>();
        int minLength = Integer.MAX_VALUE;
        for (Vertex endVertex : res) {
            List<String> ladder = new ArrayList<>();
            Vertex iter = endVertex;
            int length = 0;
            while (iter != null) {
                ladder.add(iter.word);
                iter = iter.pre;
                length++;
            }
            minLength = Math.min(minLength, length);
            ladders.add(ladder);
        }

        List<List<String>> minLengthLadders = new ArrayList<>();
        for (List<String> ladder : ladders) {
            if (ladder.size() == minLength) {
                List<String> minLadder = new ArrayList<>();
                for (int i = ladder.size() - 1; i >= 0; i--) {
                    minLadder.add(ladder.get(i));
                }
                minLengthLadders.add(minLadder);
            }
        }

        return minLengthLadders;
    }

    private Graph buildGraph(String begin, List<String> dict) {
        Set<String> words = new HashSet<>(dict);
        words.add(begin);
        Graph graph = new Graph(new ArrayList<>(words));
        for (String word : words) {
            char[] charArray = word.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char ch = charArray[i];
                for (char change = 'a'; change <= 'z'; change++) {
                    if (change != ch) {
                        charArray[i] = change;
                        String nextWord = new String(charArray);
                        if (words.contains(nextWord)) {
                            graph.addEdge(word, nextWord);
                        }
                    }
                }
                charArray[i] = ch;
            }
        }
        return graph;
    }

    public static void main(String[] args) {
//        System.out.println(new WordLadderII().findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
//        System.out.println(new WordLadderII().findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log")));
        System.out.println(new WordLadderII().findLadders("hot", "dog", Arrays.asList("hot","dog")));
    }
}
