package geekspearls.amz;

import java.util.HashSet;
import java.util.Set;

public class KSizeDistinctSubstring {

    /**
     * Given a string and a number k. Find all sub strings of size k with k distinct characters.
     *
     * Example:
     *
     * input: abcdkeewrf and 4
     *
     * output: abcd, bcdk, cdke,ewrf
     */
    public Set<String> kSizeDistinctSubstrings(String str, int k) {
        Set<String> distincts = new HashSet<>();

        for (int i = 0; i + k <= str.length(); i++) {
            distincts.add(str.substring(i, i + k));
        }

        return distincts;
    }
}
