package geekspearls.amz.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 * Machine 1 (sender) has the function:
 *
 * String encode(List<String> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 *
 * Machine 2 (receiver) has the function:
 * List<String> decode(String s) {
 *   //... your code
 *   return strs;
 * }
 */
public class EncodeAndDecodeStrings {

    /**
     * size1#str1 + size2#str2 + ...
     */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append("#").append(s);
        }
        return sb.toString();
    }

    /**
     * 4#abc#2#df
     */
    public List<String> decode(String str) {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < str.length();) {
            int hash = str.indexOf('#', i);
            int size = Integer.valueOf(str.substring(i, hash));
            String s = str.substring(hash + 1, hash + 1 + size);
            strs.add(s);
            i = hash + 1 + size;
        }
        return strs;
    }

    public static void main(String[] args) {
        EncodeAndDecodeStrings eds = new EncodeAndDecodeStrings();
        String str = "4#abc#2#df";
        List<String> strList = eds.decode(str);
        System.out.println(eds.encode(strList));
    }
}
