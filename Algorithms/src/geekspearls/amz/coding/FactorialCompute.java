package geekspearls.amz.coding;

/**
 * Status: Employed, 6+ YOE
 * Position: SDE2 at Amazon AWS
 * Location: Denver, CO
 * Date: February 3, 2020
 *
 * I wanted to let everyone know that you can't assume you will always get a question from leetcode. I just had a coding screen with Amazon AWS and here was my question:
 *
 * You have a class with two methods that calculate factorials.
 *
 * example: factorial is 3! = 3 * 2 * 1 = 6
 * input is 3, result is 6
 * Implement an iterative function that calculates the factorial
 * Implement a recursive function that calculates the factorial
 *
 * https://www.geeksforgeeks.org/java-program-for-factorial-of-a-number/
 * follow up: factorial of large number
 * https://www.geeksforgeeks.org/factorial-large-number/
 *
 */
public class FactorialCompute {

    public static int factorialRec(int n) {
        if (n == 1) {
            return 1;
        }
        return factorialRec(n - 1) * n;
    }

    public static int factorialIter(int n) {
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }

    /**
     * n >= 20
     */
    public static String largeFactorial(int n) {
        int[] result = new int[500];
        result[0] = 1;
        for (int i = 1; i <= n; i++) {
            int carry = 0;
            for (int j = 0; j < result.length; j++) {
                int partRes = i * result[j] + carry;
                carry = partRes / 10;
                result[j] = partRes % 10;
            }
        }
        StringBuilder strRes = new StringBuilder();
        boolean isLeadingZero = true;
        for (int i = result.length - 1; i >= 0; i--) {
            if (result[i] != 0) {
                isLeadingZero = false;
            }
            if (isLeadingZero && result[i] == 0) {
                continue;
            }
            strRes.append(result[i]);
        }
        return strRes.toString();
    }

    public static void main(String[] args) {
        System.out.println("Factorial Recursion of 5: " + factorialRec(5));
        System.out.println("Factorial Iteration of 5: " + factorialIter(5));
        System.out.println("Factorial Iteration of 20: " + largeFactorial(20));
    }
}
