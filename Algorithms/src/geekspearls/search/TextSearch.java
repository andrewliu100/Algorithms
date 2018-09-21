package geekspearls.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem to resolve:
 * Given a text and a pattern, search for occurrences of the pattern within the text.
 *
 * This class has a naive implementation and KMP implementation.
 *
 */
public class TextSearch {

    /**
     * Brute force implementation. Time complexity is O(mn) with m is the size of the
     * text and n is the size of the search pattern.
     *
     * @param text The text string to search in
     * @param pattern The pattern to search for
     * @return A list of index where the pattern is found
     */
    public List<Integer> naiveSearch(String text, String pattern) {
        List<Integer> foundIdx = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            int matchIdx = i;
            int j = 0;
            for (; matchIdx < text.length() && j < pattern.length(); j++) {
                if (text.charAt(matchIdx) == pattern.charAt(j)) {
                    matchIdx++;
                } else {
                    break;
                }
            }
            if (j == pattern.length()) {
                foundIdx.add(matchIdx - pattern.length());
            }

        }

        return foundIdx;
    }

    public List<Integer> kmpSearch(String text, String pattern) {
        int[] partialMatchTable = buildPartialMatchTable(pattern);
        List<Integer> foundIdx = new ArrayList<>();

        int i = 0, j = 0;
        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            // pattern found
            if (j == pattern.length()) {
                foundIdx.add(i - pattern.length());
                j = partialMatchTable[j - 1];
            } else if (text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) {
                    j = partialMatchTable[j - 1];
                } else {
                    i++;
                }
            }
        }

        return foundIdx;
    }

    /**
     * Build the partial match table. A partial match table is an array of the
     * length for the longest proper prefix of the pattern which is also a suffix
     * of the pattern.
     *
     * A partial match table for pattern "abababc" is as below.
     *
     *     | a | b | a | b | a | b | c | a |
     * idx | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
     * val | 0 | 0 | 1 | 2 | 3 | 4 | 0 | 1 |
     *
     * @param pattern
     * @return
     */
    private int[] buildPartialMatchTable(String pattern) {
        int[] pmt = new int[pattern.length()];
        // Length at idx 0 is always 0
        pmt[0] = 0;
        // the length of longest proper prefix which is also suffix,
        // it also points to the index of the last char in the proper prefix.
        int len = 0;
        int i = 1; // The cursor, start from 1

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                pmt[i] = len;
                i++;
            } else {
                // There is no proper prefix which is also suffix
                if (len == 0) {
                    pmt[i] = 0;
                    i++;
                } else {
                    len = pmt[len - 1];
                }
            }

        }

        return pmt;
    }

}
