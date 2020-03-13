package geekspearls.amz.OA;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are
 * all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
public class NumberOfClusters {

    public static int getNumOfClusters(int[][] grid) {
        int m = grid.length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    dfsMark(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void dfsMark(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] != 1) {
            return;
        }
        grid[x][y] = 0;
        dfsMark(grid, x - 1, y);
        dfsMark(grid, x, y + 1);
        dfsMark(grid, x + 1, y);
        dfsMark(grid, x, y - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
                        {1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 0, 1, 1}
        };
        System.out.println(getNumOfClusters(grid));
    }
}
