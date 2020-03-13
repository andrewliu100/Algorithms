package geekspearls.amz.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Position: SDE2, AWS
 * Location: Vancouver, Canada
 *
 * LP part:
 *
 * Tell me about the most significant technical challenge. How did you resolve it?
 * Tell me about the case, when you simplified a complicated solution? How did you simplify?
 * Why was the first version over-complicated?
 *
 * Tech part:
 * The question was a modified version of https://leetcode.com/problems/two-sum/
 * But instead of "return indices of the two numbers" there was requirement "return earliest indices of the two numbers".
 */
public class TwoSum {

    public static List<Integer> twoSum(List<Integer> nums, int target) {
        Map<Integer, Integer> numToIdx = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int complement = target - nums.get(i);
            if (numToIdx.containsKey(complement)) {
                return Arrays.asList(numToIdx.get(complement), i);
            }
            numToIdx.put(nums.get(i), i);
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(twoSum(Arrays.asList(7, 2, 1, 8), 9));
    }
}
