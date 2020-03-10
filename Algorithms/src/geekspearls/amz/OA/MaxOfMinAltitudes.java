package geekspearls.amz.OA;

/**
 * Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and
 * ending at [r-1, c-1]. The score of a path is the minimum value in that path. For example,
 * the score of the path 8 → 4 → 5 → 9 is 4.
 *
 * Don't include the first or final entry. You can only move either down or right at any point in time.
 *
 * Example 1:
 *
 * Input:
 * [[5, 1],
 *  [4, 5]]
 *
 * Output: 4
 * Explanation:
 * Possible paths:
 * 5 → 1 → 5 => min value is 1
 * 5 → 4 → 5 => min value is 4
 * Return the max value among minimum values => max(4, 1) = 4.
 * Example 2:
 *
 * Input:
 * [[1, 2, 3]
 *  [4, 5, 1]]
 *
 * Output: 4
 * Explanation:
 * Possible paths:
 * 1-> 2 -> 3 -> 1
 * 1-> 2 -> 5 -> 1
 * 1-> 4 -> 5 -> 1
 * So min of all the paths = [2, 2, 4]. Note that we don't include the first and final entry.
 * Return the max of that, so 4.
 */
public class MaxOfMinAltitudes {

    /**
     * DP.
     * Because can only move down and right, so:
     * score[0][0] = Integer.MAX_VALUE
     * score[i][j] = max(min(score[i-1][j], grid[i][j]), min(score[i][j-1], grid[i][j]))
     *
     * Time: O(m*n)
     * Space: O(m*n)
     */
    public static int maxScore(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        // Grid has only first and last entries
        if (row * col == 2) {
            return 0;
        }


        int[][] score = new int[row][col];
        // Exclude last entry
        grid[row - 1][col - 1] = Integer.MAX_VALUE;
        // Init first entry
        score[0][0] = Integer.MAX_VALUE;
        // Init first row
        for (int i = 1; i < col; i++) {
            score[0][i] = Math.min(score[0][i - 1], grid[0][i]);
        }
        // Init first col
        for (int i = 1; i < row; i++) {
            score[i][0] = Math.min(score[i - 1][0], grid[i][0]);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                score[i][j] = Math.max(Math.min(score[i-1][j], grid[i][j]), Math.min(score[i][j-1], grid[i][j]));
            }
        }

        return score[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] grid1 = new int[][] { {5, 1}, {4, 5} };                        // 4
        int[][] grid2 = new int[][] { {5, 1, 7}, {4, 8, 5} };                  // 4
        int[][] grid3 = new int[][] { {1, 9, 9}, {9, 9, 9}, {9, 9, 9} };       // 1 (if the first entry is not considered)
        int[][] grid4 = new int[][] { {10, 7, 3}, {12, 11, 9}, {1, 2, 8} };    // 8 (same reason)
        int[][] grid5 = new int[][] { {20, 20, 3}, {20, 3, 20}, {3, 20, 20} }; // 3
        int[][] grid6 = new int[][] { {5, 1}};                        // 0
        int[][] grid7 = new int[][] { {5, 5, 3, 1}};                        // 0
        int[][] grid8 = new int[][] { {} };                        // 0
        int[][] grid9 = new int[][] {};                        // 0
        System.out.println("grid1: Expected: 4, Actual: " + maxScore(grid1));
        System.out.println("grid2: Expected: 4, Actual: " + maxScore(grid2));
        System.out.println("grid3: Expected: 9, Actual: " + maxScore(grid3));
        System.out.println("grid4: Expected: 9, Actual: " + maxScore(grid4));
        System.out.println("grid5: Expected: 3, Actual: " + maxScore(grid5));
        System.out.println("grid5: Expected: 0, Actual: " + maxScore(grid6));
        System.out.println("grid5: Expected: 3, Actual: " + maxScore(grid7));
        System.out.println("grid5: Expected: 0, Actual: " + maxScore(grid8));
        System.out.println("grid5: Expected: 0, Actual: " + maxScore(grid9));
    }
}
