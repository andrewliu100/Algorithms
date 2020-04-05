package geekspearls.amz.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-score-words-formed-by-letters/
 *
 * Given a list of words, list of  single letters (might be repeating) and score of every character.
 *
 * Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be used two or more times).
 *
 * It is not necessary to use all characters in letters and each letter can only be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
 * Output: 23
 * Explanation:
 * Score  a=1, c=9, d=5, g=3, o=2
 * Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
 * Words "dad" and "dog" only get a score of 21.
 * Example 2:
 *
 * Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
 * Output: 27
 * Explanation:
 * Score  a=4, b=4, c=4, x=5, z=10
 * Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
 * Word "xxxz" only get a score of 25.
 * Example 3:
 *
 * Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
 * Output: 0
 * Explanation:
 * Letter "e" can only be used once.
 *
 * The real interview requires output the words forming the max score
 *
 */
public class MaxScoreWords {

    class Result {
        int maxScore;
        List<String> words = new ArrayList<>();

        public String toString() {
            return "Score: " + maxScore + " of words: " + words;
        }
    }

    public Result maxScoreWords(String[] words, char[] letters, Map<Character, Integer> scores) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char ch : letters) {
            int count = counts.getOrDefault(ch, 0);
            counts.put(ch, ++count);
        }
        return findMaxScoreWords(words, 0, counts, scores);
    }

    /**
     * words list in result is not right!!!
     */
    private Result findMaxScoreWords(String[] words, int index, Map<Character, Integer> counts, Map<Character, Integer> scores) {
        Result res = new Result();
        for (int i = index; i < words.length; i++) {
            int wordScore = 0;
            for (char ch : words[i].toCharArray()) {
                int count = counts.getOrDefault(ch, 0);
                if (count <= 0) {
                    wordScore = 0;
                    break;
                }
                wordScore += scores.getOrDefault(ch, 0);
                counts.put(ch, --count);
            }
//            System.out.println("index: " + index + " loop: " + i + " word: " + words[i] + " score: " + wordScore);
            if (wordScore != 0) {
                res.words.add(words[i]);
//                System.out.println("index: " + index + " loop: " + i + " words: " + res.words);
                Result subResult = findMaxScoreWords(words, i + 1, counts, scores);
//                System.out.println("index: " + index + " loop: " + i + " word: " + words[i] + " subResult: " + subResult);
                wordScore += subResult.maxScore;
                if (wordScore > res.maxScore) {
//                    System.out.println("index: " + index + " loop: " + i + " wordScore: " + wordScore);
                    res.maxScore = wordScore;
                }
            }
            // backtracking
            wordScore = 0;
            for (char ch : words[i].toCharArray()) {
                int count = counts.getOrDefault(ch, -1);
                counts.put(ch, ++count);
            }

        }
        System.out.println("index: " + index + " result: " + res);
        return res;
    }

    public static void main(String[] args) {
        MaxScoreWords msw = new MaxScoreWords();
        String[] words = {"dog","cat","dad","good"};
        char[] letters = {'a','a','c','d','d','d','g','o','o'};
        Map<Character, Integer> scores = new HashMap<>();
        scores.put('a', 1);
        scores.put('c', 9);
        scores.put('d', 5);
        scores.put('g', 3);
        scores.put('o', 2);
        System.out.println(msw.maxScoreWords(words, letters, scores));
    }
}
