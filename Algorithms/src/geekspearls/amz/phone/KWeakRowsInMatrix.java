package geekspearls.amz.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
        System.out.println(getWeakRows(matrix, k));

    }

    /**
     * O(mlogn)
     */
    private static List<Integer> getWeakRows(int[][] matrix, int k) {
        Queue<int[]> minHeap = new PriorityQueue<>((a, b)->a[1] - b[1]);
        for(int i=0;i<matrix.length;i++) {
            int[] row = matrix[i];
            int num = getNumOf1(row);
            minHeap.offer(new int[] {i, num}); // O(logn)
        }
        List<Integer> res = new ArrayList<>();
        while(k > 0 && !minHeap.isEmpty()) {
            res.add(minHeap.poll()[0]);
            k--;
        }
        return res;
    }

    /**
     * O(logn)
     */
    private static int getNumOf1(int[] row) {
        int l = 0, r = row.length-1;
        while(l < r) {
            int m = l + (r - l)/2;
            if(row[m] == 0) {
                r = m;
            }else {
                l = m+1;
            }
        }
        return l;
    }
}
