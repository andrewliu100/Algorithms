package geekspearls.amz.OA;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an int array nums and an int target, find how many unique pairs in the array such that their sum is equal to
 * target. Return the number of pairs.
 *
 * Example 1:
 *
 * Input: nums = [1, 1, 2, 45, 46, 46], target = 47
 * Output: 2
 * Explanation:
 * 1 + 46 = 47
 * 2 + 45 = 47
 * Example 2:
 *
 * Input: nums = [1, 1], target = 2
 * Output: 1
 * Explanation:
 * 1 + 1 = 2
 * Example 3:
 *
 * Input: nums = [1, 5, 1, 5], target = 6
 * Output: 1
 * Explanation:
 * [1, 5] and [5, 1] are considered the same.
 */
public class TwoSumUniquePairs {

    /**
     * Time: O(n)
     * Space: O(n)
     */
    public static int uniquePairs(List<Integer> nums, int target) {
        Set<Integer> complements = new HashSet<>();
        Set<Integer> seenNums = new HashSet<>();
        int count = 0;
        for (int num : nums) {
            int complement = target - num;
            if (complements.contains(complement) && !seenNums.contains(num)) {
                count++;
                seenNums.add(num);
                seenNums.add(complement);
            } else {
                complements.add(num);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(uniquePairs(Arrays.asList(1, 1, 2, 45, 46, 46), 47));
        System.out.println(uniquePairs(Arrays.asList(1, 1), 2));
        System.out.println(uniquePairs(Arrays.asList(1, 5, 1, 5), 6));
    }
}
