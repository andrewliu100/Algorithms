package geekspearls.amz.OA;

import java.util.List;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * Example:
 *
 * Consider the following matrix:
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 */
public class Search2DMatrixII {

    /**
     * Start from top right corner.
     * If the value equals the target, return true.
     * If the target is greater than the value, it can't exist in the current row.
     * If the target is less than the value, it can't exist in the current column.
     *
     * Time: O(m+n) because we eliminate one row or column at one time.
     * Space: O(1)
     */
    public static boolean search(List<List<Integer>> matrix, int target) {
        if (matrix == null || matrix.isEmpty() || matrix.get(0).isEmpty()) {
            return false;
        }

        int col = matrix.get(0).size() - 1;
        int row = 0;
        while (col > 0 && row < matrix.size()) {
            int val = matrix.get(row).get(col);
            if (val == target) {
                return true;
            } else if (target > val) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }
}
