package geekspearls.amz.phone;

import java.util.PriorityQueue;

/**
 * Given a string with lowercase, uppercase and numbers, rearrange it so that uppercase letters are first,
 * then lowercase and then numbers. It should maintain the original order.
 *
 * Input- "cdBnC52c" output - "BCcdnc52"
 * Input - "123abA" output - "Aab123"
 *
 * Rearrange it in o(n) in one pass without using extra space.
 */
public class WordRearrange {

    public String rearrangeWord(String word) {
        return new String(rearrange(word.toCharArray()));
    }

    /**
     * O(n). This doesn't maintain the original order of chars
     */
    private char[] rearrange(char[] chars) {
        int bottom = 0;
        int mid = 0;
        int top = chars.length - 1;

        while (mid <= top) {
            if (Character.isUpperCase(chars[mid])) {
                swap(chars, bottom, mid);
                bottom++;
                mid++;
            }
            if (Character.isLowerCase(chars[mid])) {
                mid++;
            }
            if (Character.isDigit(chars[mid])) {
                swap(chars, mid, top);
                mid++;
                top--;
            }
        }
        return chars;
    }

    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
/////////////////////////////////////////////////////////////////

    class CharWithIdx implements Comparable<CharWithIdx> {
        char ch;
        int idx;

        CharWithIdx(char ch, int idx) {
            this.ch = ch;
            this.idx = idx;
        }

        public int compareTo(CharWithIdx that) {
            if (Character.isUpperCase(this.ch)) {
                if (!Character.isUpperCase(that.ch)) {
                    return -1;
                } else {
                    return Integer.compare(this.idx, that.idx);
                }
            } else if (Character.isLowerCase(this.ch)) {
                if (Character.isDigit(that.ch)) {
                    return -1;
                } else if (Character.isUpperCase(that.ch)) {
                    return 1;
                } else {
                    return Integer.compare(this.idx, that.idx);
                }
            } else if (Character.isDigit(this.ch)) {
                if (!Character.isDigit(that.ch)) {
                    return 1;
                } else {
                    return Integer.compare(this.idx, that.idx);
                }
            }
            return Integer.compare(this.idx, that.idx);
        }
    }

    public String rearrange(String str) {
        PriorityQueue<CharWithIdx> heap = new PriorityQueue<>();
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            heap.add(new CharWithIdx(array[i], i));
        }
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) {
            sb.append(heap.poll().ch);
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        System.out.println(new WordRearrange().rearrangeWord("cdBnC52c"));
        System.out.println(new WordRearrange().rearrangeWord("123abA"));
        System.out.println(new WordRearrange().rearrangeWord("cDBnC52c"));

        System.out.println(new WordRearrange().rearrange("cdBnC52c"));
        System.out.println(new WordRearrange().rearrange("123abA"));
        System.out.println(new WordRearrange().rearrange("cDBnC52c"));
    }
}
