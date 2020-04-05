package geekspearls.amz.coding;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to
 * the very right. You can only see the k numbers in the window. Each time the sliding window moves right by
 * one position. Return the max sliding window.
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * Follow up:
 * Could you solve it in linear time?
 */
public class SlidingWindow {

    public List<Integer> slidingWindowMax(List<Integer> nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> max = new ArrayList<>();
        for (int i = 0; i <= nums.size(); i++) {
            if (i == nums.size()) {
                max.add(getMaxFromQueue(queue));
            } else {
                int num = nums.get(i);
                if (queue.size() == k) {
                    max.add(getMaxFromQueue(queue));
                    queue.removeFirst();
                }
                queue.addLast(num);
            }
        }
        return max;
    }

    public Integer getMaxFromQueue(Deque<Integer> queue) {
        Deque<Integer> q = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Integer n = queue.removeFirst();
            max = Math.max(max, n);
            q.addLast(n);
        }
        while (!q.isEmpty()) {
            queue.addLast(q.removeFirst());
        }
        return max;
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,3,-1,-3,5,3,6,7);
        System.out.println(new SlidingWindow().slidingWindowMax(nums, 3));
    }
}
