package geekspearls.amz.coding;

/**
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class DutchFlagSort {

    /**
     * two passes
     *
     * O(n)
     */
    public void sortColors1(int[] nums) {
        int[] counts = new int[3];
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            counts[n]++;
        }
        for (int i = 0; i < counts[0]; i++) {
            nums[i] = 0;
        }
        for (int i = 0; i < counts[1]; i++) {
            nums[i + counts[0]] = 1;
        }
        for (int i = 0; i < counts[2]; i++) {
            nums[i + counts[0] + counts[1]] = 2;
        }
    }

    /**
     * one pass
     */
    public void sortColor2(int[] nums) {
        int p0 = 0; // position of 0s
        int p1 = 0; // position of 1s
        int p2 = nums.length - 1; // position of 2s

        while (p1 <= p2) {
            if (nums[p1] == 1) {
                p1++;
            } else if (nums[p1] == 0) {
                swap(nums, nums[p0], nums[p1]);
                p0++;
                p1++;
            } else { // nums[p1] == 2
                swap(nums, nums[p1], nums[p2]);
                p2--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
