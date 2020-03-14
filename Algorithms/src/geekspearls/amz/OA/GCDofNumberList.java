package geekspearls.amz.OA;

/**
 * Calc the GCD of a list of numbers
 */
public class GCDofNumberList {

    public static int gcd(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result = gcd(result, nums[i]);
            if (result == 1) {
                return 1;
            }
        }
        return result;
    }

    private static int gcd(int x, int y) {
        return y == 0 ? x : gcd(x, x % y);
    }
}
