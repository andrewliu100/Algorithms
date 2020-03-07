package geekspearls.amz;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that
 * each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {

    /**
     * 1. Record the last occurrence of each letter
     * 2. Go through each letter in S, record the max index of the last occurrence of each letter.
     * 3. A valid partition of label is when the index is the same as the max index of the last occurrence.
     *
     * Time: O(n)
     * Space: O(n)
     */
    public List<Integer> partitionLabels(String s) {
        int[] lastOccur = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastOccur[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();

        int startOfLabel = 0;
        int lastPosition = 0;
        for (int i = 0; i < s.length(); i++) {
            lastPosition = Math.max(lastPosition, lastOccur[s.charAt(i) - 'a']);
            if (i == lastPosition) {
                result.add(i - startOfLabel + 1);
                startOfLabel = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        PartitionLabels partitionLabels = new PartitionLabels();
        System.out.println(partitionLabels.partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(partitionLabels.partitionLabels(""));
    }
}
