package geekspearls.misc;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    private List<int[]> perms = new ArrayList<>();

    /**
     * Generate all permutations of numbers from 1 to n;
     *
     * @return A list of int array which are all permutations.
     */
    public List<int[]> permGen(int n) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        generate(nums, n);
        return perms;
    }

    private void generate(int[] nums, int n) {
        if (n == 1) {
            perms.add(nums.clone());
            return;
        }

        for (int i = 0; i < n; i++) {
            swap(nums, i, n - 1);
            generate(nums, n - 1);
            swap(nums, i, n - 1);
        }
    }

    /**
     * Generate all permutations of numbers from 1 to n in ascending order
     *
     * @return A list of int array which are all permutations in ascending order
     */
    public List<int[]> sortedPermGen(int n) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }
        sortedGenerate(nums);
        return perms;
    }

    private void sortedGenerate(int[] nums) {
        perms.add(nums.clone());
        int n = factorial(nums.length);
        for (int i = 1; i <= n; i++) {
            perms.add(nextPermGen(nums).clone());
        }
    }

    /**
     * Find the next permutation to the given permutation and add it to the result.
     *
     * [1, 3, 5, 4, 2]
     * 1. From right to left, find the first decrement elem a[i] = 3
     * 2. From right to left, find the element a[j] just greater than a[i]. a[j] = 4
     * 3. Swap a[i] and a[j]. [1, 4, 5, 3, 2]
     * 4. Reverse a[i + 1] to a[n - 1]. [1, 4, 2, 3, 5]
     */
    private int[] nextPermGen(int[] nums) {
        int n = nums.length;
        // step 1
        int i = n - 2;
        while (i > -1 && nums[i] > nums[i + 1]) {
            i--;
        }

        if (i != -1) {
            // step 2
            int j = n - 1;
            while (j > -1 && nums[j] < nums[i]) {
                j--;
            }

            // step 3
            swap(nums, i, j);

            // step 4
            reverse(nums, i + 1);
        } else {
            reverse(nums, 0);
        }
        return nums;
    }

    /**
     * Reverse the elements starting at index i to the end
     */
    private void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int factorial(int n) {
        int res = 1;
        for (int i = n; i >= 1; i--) {
            res *= i;
        }
        return res;
    }
}
