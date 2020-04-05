package geekspearls.amz.coding;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * There are soldiers and civilians arranged in N x M matrix way, find out the 'K' weak rows in the matrix.
 * Weak rows are those where numbers of soldiers are less compare to other siblings row.
 * Soldiers are always stand in frontier, means always 1's may appear first and then 0's
 * 1 represents soldier
 * 0 represents civilian
 *
 * ex:
 * K = 2
 * matrix = [
 * [1, 1, 1, 0, 0, 0]
 * [1, 1, 0, 0, 0, 0]
 * [1, 1, 1, 1, 0, 0]
 * [1, 1, 0, 0, 0, 0]
 * ]
 * here row 1 & 3 are weak rows since they have less numbers of 1's compare to row 0 & 2
 */
public class KWeakRowsInMatrix {

    public static void main(String[] args) {
        int k = 2;
        int[][]	matrix = {
                        {1, 1, 1, 0, 0, 0},
                        {1, 1, 1, 1, 0, 0},
                        {1, 1, 0, 0, 0, 0},
                        {1, 1, 0, 0, 0, 0}
        };
        System.out.println(findWeakRows(matrix, k));

    }

    static class Row implements Comparable<Row> {
        int[] values;
        int rowIdx;

        Row(int[] values, int rowIdx) {
            this.values = values;
            this.rowIdx = rowIdx;
        }

        /**
         * O(logn)
         */
        int numOf1s() {
            int low = 0;
            int high = values.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (values[mid] == 1) {
                    low = mid + 1;
                } else if (values[mid] == 0) {
                    high = mid - 1;
                }
            }
            return low;
        }

        public int compareTo(Row that) {
            return Integer.compare(this.numOf1s(), that.numOf1s());
        }
    }

    /**
     * O(mlogn)
     */
    public static List<Integer> findWeakRows(int[][] matrix, int k) {
        PriorityQueue<Row> minHeap = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            minHeap.add(new Row(matrix[i], i));
        }

        List<Integer> weakRows = new ArrayList<>();
        while (weakRows.size() < k && !minHeap.isEmpty()) {
            weakRows.add(minHeap.poll().rowIdx);
        }
        return weakRows;
    }
}
