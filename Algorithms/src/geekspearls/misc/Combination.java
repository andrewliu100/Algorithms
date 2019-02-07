package geekspearls.misc;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    List<List<Integer>> combs = new ArrayList<>();

    public List<List<Integer>> combination(int n, int k) {
        int[] nums = new int[n];
        for (int i = 1; i <= n; i++) {
            nums[i - 1] = i;
        }

        int[] data = new int[k];
        combi(nums, data, 0, 0, k);
        return combs;
    }

    private void combi(int[] nums, int[] data, int idx, int from, int k) {
        if (idx == k) {
            combs.add(toList(data));
            return;
        }

        for (int i = from; i < nums.length; i++) {
            data[idx] = nums[i];
            combi(nums, data, idx + 1, i + 1, k);
        }
    }

    private List<Integer> toList(int[] data) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            list.add(data[i]);
        }
        return list;
    }
}
