package geekspearls.amz.OA;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParentheses {

    private static List<String> result = new ArrayList<>();

    /**
     * Count the number of "(" and ")" in recursive calls.
     *
     * Time: O(4^n/sqr(n)) n-th Catalan number
     */
    public static List<String> generateParentheses(int n) {
        recursiveGen("", 0, 0, n);
        return result;
    }

    private static void recursiveGen(String prefix, int open, int close, int n) {
        if (prefix.length() == n * 2) {
            result.add(prefix);
            return;
        }
        if (open < n) {
            recursiveGen(prefix + "(", open + 1, close, n);
        }
        if (close < open) {
            recursiveGen(prefix + ")", open, close + 1, n);
        }
    }

    public static void main(String[] args) {
        System.out.println(generateParentheses(3));
    }
}
