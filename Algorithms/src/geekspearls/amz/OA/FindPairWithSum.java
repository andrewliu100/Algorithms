package geekspearls.amz.OA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of positive integers nums and an int target, return indices of the two numbers such that they add up to
 * a target - 30.
 *
 * Conditions:
 *
 * You will pick exactly 2 numbers.
 * You cannot pick the same element twice.
 * If you have muliple pairs, select the pair with the largest number.
 * Example 1:
 *
 * Input: nums = [1, 10, 25, 35, 60], target = 90
 * Output: [2, 3]
 * Explanation:
 * nums[2] + nums[3] = 25 + 35 = 60 = 90 - 30
 * Example 2:
 *
 * Input: nums = [20, 50, 40, 25, 30, 10], target = 90
 * Output: [1, 5]
 * Explanation:
 * nums[0] + nums[2] = 20 + 40 = 60 = 90 - 30
 * nums[1] + nums[5] = 50 + 10 = 60 = 90 - 30
 * You should return the pair with the largest number.
 */
public class FindPairWithSum {

    /**
     * Hashmap. Use a variable to store the largest num from the pair to handle the case of multiple pairs
     * Time and Space: O(n)
     */
    public static List<Integer> findPair(List<Integer> nums, int target) {
        target = target - 30;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> pair = Arrays.asList(-1, -1);
        int largest = 0;
        for (int i = 0; i < nums.size(); i++) {
            int complement = target - nums.get(i);
            if ((nums.get(i) > largest || complement > largest) && map.containsKey(complement)) {
                pair.set(0, map.get(complement));
                pair.set(1, i);
                largest = Math.max(nums.get(i), complement);
            }
            map.put(nums.get(i), i);
        }
        return pair;
    }

    public static void main(String[] args) {
        System.out.println(findPair(Arrays.asList(1, 10, 25, 35, 60), 90));
        System.out.println(findPair(Arrays.asList(20, 50, 40, 25, 30, 10), 90));
        System.out.println(findPair(Arrays.asList(5, 55, 40, 20, 30, 30), 90));
    }
}
