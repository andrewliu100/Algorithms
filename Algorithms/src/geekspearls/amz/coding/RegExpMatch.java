package geekspearls.amz.coding;

/**
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class RegExpMatch {

    /**
     * DP
     *
     * init:
     *    '' x a * b . c
     * '' T  F F F F F F
     * x  F
     * a  F
     * a  F
     * b  F
     * y  F
     * c  F
     *
     *            T[i-1][j-1]                                    if s[i] == p[j] || p[j] = '.'
     *            T[i][j-2]    //zero occurrence of p[j-1]       if p[j] == '*'
     * T[i,j] =   T[i-1][j]    if t[i] == p[j-1] || p[j-1] == '.'  if p[j] == '*'
     *            False                                          otherwise
     *
     *
     */
    public boolean isMatch(String str, String pattern) {
        int m = str.length() + 1;
        int n = pattern.length() + 1;
        boolean[][] matrix = new boolean[m][n];

        //init
        matrix[0][0] = true;

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                char p = pattern.charAt(j - 1);
                char s = pattern.charAt(i - 1);
                if (p == s || p == '.') {
                    matrix[i][j] = matrix[i-1][j-1];
                } else if (p == '*') {
                    if (s == pattern.charAt(j - 2) || pattern.charAt(j - 2) == '.') {
                        matrix[i][j] = matrix[i-1][j];
                    } else {
                        matrix[i][j] = matrix[i][j-2];
                    }
                }
            }
        }

        return matrix[m-1][n-1];
    }

    public static void main(String[] args) {
        RegExpMatch matcher = new RegExpMatch();
        System.out.println(matcher.isMatch("xaabyc", "xa*b.c"));
        System.out.println(matcher.isMatch("aab", "c*a*b")); //???
    }
}
