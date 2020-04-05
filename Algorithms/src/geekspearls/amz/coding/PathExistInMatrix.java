package geekspearls.amz.coding;

import java.util.Arrays;

/**
 * Skier going down a mountain.
 * Write a program where you have a 2D array of MxN, starting at (0,0) and needs to return true if they can reach the bottom
 * right-most index (M, N). Return false if they can not.
 * Skier can only traverse through portions of the array marked 0. If there is a 1 then the skier can not traverse that index.
 * The skier can only go down or right.
 *
 * Follow-up
 * What if the skier can go left as well.
 */
public class PathExistInMatrix {

    static class Coordinate {
        int x, y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * DFS
     */
    public static boolean isPathExist(int[][] matrix) {
        return dfs(matrix, new Coordinate(0, 0));
    }

    /**
     * If can go left or up, set the visited cell to 1!
     */
    private static boolean dfs(int[][] matrix, Coordinate cur) {
        if (cur.x < 0 || cur.x >= matrix.length || cur.y < 0 || cur.y >= matrix[0].length || matrix[cur.x][cur.y] == 1) {
            return false;
        }
        if (cur.x == matrix.length - 1 && cur.y == matrix[0].length - 1) {
            return true;
        }

        // If can go left or up
        matrix[cur.x][cur.y] = 1;

        for (Coordinate next : Arrays.asList(new Coordinate(cur.x + 1, cur.y),
                        new Coordinate(cur.x, cur.y + 1),
                        new Coordinate(cur.x, cur.y - 1))) {
            if (dfs(matrix, next)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                        {0, 1, 1, 0, 1},
                        {0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0}
        };
        System.out.println(isPathExist(matrix));

        int[][] matrix2 = {
                        {0, 0, 0, 0, 1},
                        {1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 1},
                        {0, 1, 0, 1, 0},
                        {0, 1, 0, 0, 0}
        };
        System.out.println(isPathExist(matrix2));
    }
}
