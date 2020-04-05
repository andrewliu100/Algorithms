package geekspearls.amz.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 *
 *
 * Example:
 *
 * Input:
 * board = [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 */
public class WordSearchII {

    class Coordinate {
        int x, y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    /**
     * O(w * l * m * n)
     * in the worst case where all words start with different characters, and there is
     * a word starting with a character in the cell board[m - 1][n - 1], we have O(m * n * l * wl). However,
     * if there are words starting with same characters and paths sharing cells, Trie can check multiple
     * words when DFS from a certain cell, rather than check only one word when DFS from a certain cell like the naive way.
     */
    public List<String> search(List<String> words, char[][] matrix) {
        List<String> found = new ArrayList<>();
        TrieNode trie = buildTrie(words);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dfs(matrix, new Coordinate(i, j), trie, found);
            }
        }

        return found;
    }

    /**
     * O(w * l) w is the size of word list, l is the length of longest word
     */
    private TrieNode buildTrie(List<String> words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char ch : w.toCharArray()) {
                if (!p.children.containsKey(ch)) {
                    p.children.put(ch, new TrieNode());
                }
                p = p.children.get(ch);
            }
            p.word = w;
        }
        return root;
    }

    /**
     * O(m * n)
     */
    private void dfs(char[][] matrix, Coordinate curr, TrieNode p, List<String> found) {
        if (curr.x < 0 || curr.x >= matrix.length ||
            curr.y < 0 || curr.y >= matrix[0].length) {
            return;
        }

        char c = matrix[curr.x][curr.y];
        if (c == '#' || !p.children.containsKey(c)) {
            return;
        }

        p = p.children.get(c);
        if (p.word != null) {
            found.add(p.word);
            p.word = null;
        }
        matrix[curr.x][curr.y] = '#';

        for (Coordinate next : Arrays.asList(new Coordinate(curr.x - 1, curr.y),
                                            new Coordinate(curr.x, curr.y + 1),
                                            new Coordinate(curr.x + 1, curr.y),
                                            new Coordinate(curr.x, curr.y - 1))) {
            dfs(matrix, next, p, found);
        }
        matrix[curr.x][curr.y] = c;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        List<String> words = Arrays.asList("oath","pea","eat","rain");
        System.out.println(new WordSearchII().search(words, matrix));
    }
}
