/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 * <p>
 * Algorithms geekspearls.dp.Lcs
 *
 * @author Andrew Liu
 * @since 2015 30/08/2015 1:07 PM
 */
package geekspearls.dp;

import java.util.Arrays;

/**
 * Dynamic programming to resolve the Longest Common Subsequence problem.
 * Go to geekspearls.blogspot.com for details about the Longest Common Subsequence problem.
 *
 * @author Andrew
 */
public class Lcs {

    private int[][] array;

    public int lcs(String x, String y) {

        int m = x.length() + 1, n = y.length() + 1;
        array = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) { // init the first row and column to zero
                    array[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                } else {
                    array[i][j] = Math.max(array[i][j - 1], array[i - 1][j]);
                }
            }
        }
        return array[m - 1][n - 1];
    }

    public void printArray() {
        for (int[] i : array) {
            System.out.println(Arrays.toString(i));
        }
    }
}
