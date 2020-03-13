package geekspearls.amz.phone;

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

    public static void main(String[] args) {
        System.out.println(new WordRearrange().rearrangeWord("cdBnC52c"));
        System.out.println(new WordRearrange().rearrangeWord("123abA"));
        System.out.println(new WordRearrange().rearrangeWord("cDBnC52c"));
    }
}
