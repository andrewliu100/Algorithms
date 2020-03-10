package geekspearls.amz.OA;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {

    /**
     * Time O(n*n)
     * Space O(n*n)
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[] dr = new int[] {0, 1, 0, -1};
        int[] dc = new int[] {1, 0, -1, 0};
        int r = 0, c = 0, dir = 0, val = 1;
        while (val <= n * n) {
            matrix[r][c] = val;
            r += dr[dir];
            c += dc[dir];
            if (needTurn(matrix, r, c, n)) {
                // back trace
                r -= dr[dir];
                c -= dc[dir];
                dir = (dir + 1) % 4;
                r += dr[dir];
                c += dc[dir];
            }
            val++;
        }
        return matrix;
    }

    private boolean needTurn(int[][] matrix, int r, int c, int n) {
        return r == n || c == n || r == -1 || c == -1 || matrix[r][c] != 0;
    }
}
