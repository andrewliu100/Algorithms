package geekspearls.amz.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Position: Experienced Mobile Engineer
 *
 * Similar Leetcode Question:
 * https://leetcode.com/problems/summary-ranges/ (premium)
 *
 * Given an unsorted array of integers, return an array of strings where each string indicates the
 * start and end of a subsequence of consecutive integers.
 *
 * Example:
 * Input = [6, 3, 4, 9, 2, 7]
 * Output = ["2->4", "6->7", "9"]
 */
public class SummaryRanges {

    /**
     * O(nlogn) due to the sorting
     */
    public static List<String> summaryRangesSorted(int[] nums) {
        Arrays.sort(nums);

        List<String> result = new ArrayList<>();
        int counter = 0;
        int start = nums[0];
        for (int i = 1; i <= nums.length; i++) {
            counter++;
            if (i == nums.length || nums[i] != start + counter) {
                // range is broken
                int end = nums[i-1];
                if (start != end) {
                    result.add(start + "->" + end);
                } else {
                    result.add(end + "");
                }
                // reset start and counter
                if (i < nums.length) {
                    start = nums[i];
                    counter = 0;
                }
            }
        }
        return result;
    }

}
