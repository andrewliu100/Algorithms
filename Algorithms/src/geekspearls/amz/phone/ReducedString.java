package geekspearls.amz.phone;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string return a reduced string such that , the reduced string has the characters re-arranged
 *
 * in order of characters having highest frequencey
 * followed by character which appears just once in the same order as in the original string
 * and no duplicate characters
 * Example:
 *
 * Input: "hello world"
 * Output: "lohe wrd"
 * Explaination: 'l' appears thrice, 'o' appears twice, 'h','e', ' '(space) ,'w','r','d' all appear once
 * 'h','e',' ','w','r','d' should be appended in the same order as they appear in the original string  hello world
 */
public class ReducedString {

    static class CharWithCount implements Comparable<CharWithCount> {
        char ch;
        int idx;
        int count;

        CharWithCount(char ch, int idx) {
            this.ch = ch;
            this.idx = idx;
            this.count = 0;
        }

        public int compareTo(CharWithCount that) {
            int countCompare = Integer.compare(that.count, this.count);
            return countCompare != 0 ? countCompare : Integer.compare(this.idx, that.idx);
        }
    }

    public static String reduceStr(String str) {
        Map<Character, CharWithCount> map = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            CharWithCount charCount = map.getOrDefault(ch, new CharWithCount(ch, i));
            charCount.count++;
            map.put(ch, charCount);
        }

        PriorityQueue<CharWithCount> maxHeap = new PriorityQueue<>();
        for (Map.Entry<Character, CharWithCount> entry : map.entrySet()) {
            maxHeap.add(entry.getValue());
        }

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            sb.append(maxHeap.poll().ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reduceStr("hello world"));
    }
}
