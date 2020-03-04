package geekspearls.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the
 * second integer represents a value. Your task is to find an element from a and an element form b such that the sum of
 * their values is less or equal to target and as close to target as possible. Return a list of ids of selected elements.
 * If no pair is possible, return an empty list.
 *
 * Example 1:
 *
 * Input:
 * a = [[1, 2], [2, 4], [3, 6]]
 * b = [[1, 2]]
 * target = 7
 *
 * Output: [[2, 1]]
 *
 * Explanation:
 * There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
 * Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
 * Example 2:
 *
 * Input:
 * a = [[1, 3], [2, 5], [3, 7], [4, 10]]
 * b = [[1, 2], [2, 3], [3, 4], [4, 5]]
 * target = 10
 *
 * Output: [[2, 4], [3, 2]]
 *
 * Explanation:
 * There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
 * Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
 * These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
 * Example 3:
 *
 * Input:
 * a = [[1, 8], [2, 7], [3, 14]]
 * b = [[1, 5], [2, 10], [3, 14]]
 * target = 20
 *
 * Output: [[3, 1]]
 * Example 4:
 *
 * Input:
 * a = [[1, 8], [2, 15], [3, 9]]
 * b = [[1, 8], [2, 11], [3, 12]]
 * target = 20
 *
 * Output: [[1, 3], [3, 2]]
 */
public class OptimalUtilization {

    public static List<List<Integer>> optimalUtilization(int target, List<List<Integer>> a, List<List<Integer>> b) {
        List<List<Integer>> result = new ArrayList<>();
        if (a.size() == 0 || b.size() == 0) {
            return result;
        }

        a.sort(Comparator.comparingInt((List<Integer> i) -> i.get(1)));
        b.sort(Comparator.comparingInt((List<Integer> i) -> i.get(1)));
        int left = 0;
        int right = b.size() - 1;
        int maxSum = -1;
        Map<Integer, List<List<Integer>>> sumToIdPair = new HashMap<>();
        while (left < b.size() && right >= 0) {
            int sum = a.get(left).get(1) + b.get(right).get(1);
            if (sum > target) {
                right--;
            } else { // sum <= target
                // most close sum is found
                if (sum >= maxSum) {
                    sumToIdPair.putIfAbsent(sum, new ArrayList<>());
                    int iter = right;
                    // handle the duplicates
                    while (iter >= 0 && b.get(iter).get(1) == b.get(right).get(1)) {
                        List<Integer> idPair = new ArrayList<>();
                        idPair.add(a.get(left).get(0));
                        idPair.add(b.get(iter).get(0));
                        sumToIdPair.get(sum).add(idPair);
                        iter--;
                    }
                    maxSum = sum;
                }
                left++;
            }
        }
        return sumToIdPair.get(maxSum);
    }

    public static void main(String[] args) {
        List<List<Integer>> a = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(2, 5),
                Arrays.asList(3, 7),
                Arrays.asList(4, 10)
        );

        List<List<Integer>> b = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(2, 3),
                Arrays.asList(3, 4),
                Arrays.asList(4, 5)
        );
        System.out.println(optimalUtilization(10, a, b));
    }
}
