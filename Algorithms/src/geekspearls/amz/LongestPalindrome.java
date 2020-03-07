package geekspearls.amz;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindrome {

    /**
     * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
     * and there are only 2n - 12n−1 such centers.
     *
     * You might be asking why there are 2n - 12n−1 but not nn centers? The reason is the center of a palindrome can be
     * in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between
     * the two 'b's.
     *
     * Manacher's Algorithm: Time: O(n)
     *
     * Time: O(n^2) Space: O(n)
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
