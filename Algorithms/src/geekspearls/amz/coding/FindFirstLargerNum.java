package geekspearls.amz.coding;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

/**
 * Given an array of integers, for each integer in the array,
 * find the first integer after it that is greater than itself.
 *
 * input:  [1, 2, 5, 3, 4, 10, 6]
 * output: [2, 5, 10, 4, 10, -1, -1]
 *
 */
public class FindFirstLargerNum {

    public int[] findFirstLargerNum(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >=0 ;i--) {
            while (!stack.isEmpty() && stack.peekFirst() <= nums[i]) {
                stack.removeFirst();
            }
            if (stack.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = stack.peekFirst();
            }
            stack.addFirst(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 3, 4, 10, 6};
        int[] res = new FindFirstLargerNum().findFirstLargerNum(nums);

        System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
    }
}
