package geekspearls.amz.coding;

import java.util.Arrays;

/**
 * (Temple Offering)
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 */
public class CandyOffering {

    /**
     * O(n)
     */
    public int minOffering(int[] ranks) {
        int[] forward = new int[ranks.length];
        int[] backward = new int[ranks.length];
        Arrays.fill(forward, 1);
        Arrays.fill(backward, 1);

        for (int i = 1; i < ranks.length; i++) {
            if (ranks[i] > ranks[i-1]) {
                forward[i] = forward[i-1] + 1;
            }
        }

        for (int i = ranks.length - 2; i >= 0; i--) {
            if (ranks[i] > ranks[i + 1]) {
                backward[i] = backward[i+1] + 1;
            }
        }
        int minOffering = 0;
        for (int i = 0; i < ranks.length; i++) {
            minOffering += Math.max(forward[i], backward[i]);
        }
        return minOffering;
    }
}
