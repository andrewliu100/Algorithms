package geekspearls.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * You work on a team whose job is to understand the most sought after toys for the holiday season.
 * A teammate of yours has built a webcrawler that extracts a list of quotes about toys from different
 * articles. You need to take these quotes and identify which toys are mentioned most frequently.
 * Write an algorithm that identifies the top N toys out of a list of quotes and list of toys.
 *
 * Your algorithm should output the top N toys mentioned most frequently in the quotes.
 *
 * Input:
 * The input to the function/method consists of five arguments:
 *
 * numToys, an integer representing the number of toys
 * topToys, an integer representing the number of top toys your algorithm needs to return;
 * toys, a list of strings representing the toys,
 * numQuotes, an integer representing the number of quotes about toys;
 * quotes, a list of strings that consists of space-sperated words representing articles about toys
 *
 * Output:
 * Return a list of strings of the most popular N toys in order of most to least frequently mentioned
 *
 * Note:
 * The comparison of strings is case-insensitive. If the value of topToys is more than the number of toys,
 * return the names of only the toys mentioned in the quotes. If toys are mentioned an equal number of
 * times in quotes, then we need to sort based on the occurrences of each toy in different quotes,
 * if the occurrences are the same, sort alphabetically.
 *
 * Example 1:
 *
 * Input:
 * numToys = 6
 * topToys = 2
 * toys = ["elmo", "elsa", "legos", "drone", "tablet", "warcraft"]
 * numQuotes = 6
 * quotes = [
 * "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
 * "The new Elmo dolls are super high quality",
 * "Expect the Elsa dolls to be very popular this year, Elsa!",
 * "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
 * "For parents of older kids, look into buying them a drone",
 * "Warcraft is slowly rising in popularity ahead of the holiday season"
 * ];
 *
 * Output:
 * ["elmo", "elsa"]
 *
 * Explanation:
 * elmo - 4
 * elsa - 4
 * "elmo" should be placed before "elsa" in the result because "elmo" appears in 3 different quotes and
 * "elsa" appears in 2 different quotes.
 */
public class TopNBuzzWords {

    static class ToyWithCount implements Comparable<ToyWithCount> {
        String toy;
        int count;
        int mentionedTimes;

        ToyWithCount(String toy, int count, int mentionedTimes) {
            this.toy = toy;
            this.count = count;
            this.mentionedTimes = mentionedTimes;
        }

        @Override
        public int compareTo(ToyWithCount other) {
            if (this.count != other.count) {
                return this.count > other.count ? -1 : 1;
            }
            if (this.mentionedTimes != other.mentionedTimes) {
                return this.mentionedTimes > other.mentionedTimes ? -1 : 1;
            }
            return this.toy.compareToIgnoreCase(other.toy);
        }
    }

    public static List<String> topNMentioned(int numOfToys, int topN, List<String> toys, int numOfQuotes, List<String> quotes) {
        List<String[]> breakdownQuotes = breakdownQuotes(quotes);
        Set<ToyWithCount> toyMentionCounts = new TreeSet<>();
        for (String toy : toys) {
            toyMentionCounts.add(getMentionCounts(breakdownQuotes, toy));
        }

        List<String> result = new ArrayList<>();
        if (topN > numOfToys) {
            for (ToyWithCount toyWithCount : toyMentionCounts) {
                if (toyWithCount.count > 0) {
                    result.add(toyWithCount.toy);
                }
            }
        } else {
            Iterator<ToyWithCount> iter = toyMentionCounts.iterator();
            while (result.size() < topN) {
                if (iter.hasNext()) {
                    ToyWithCount toyWithCount = iter.next();
                    if (toyWithCount.count == 0) {
                        break;
                    }
                    result.add(toyWithCount.toy);
                }
            }
        }

        return result;
    }

    private static List<String[]> breakdownQuotes(List<String> quotes) {
        List<String[]> breakdowns = new ArrayList<>();
        for (String quote : quotes) {
            breakdowns.add(quote.split(" "));
        }

        return breakdowns;
    }

    private static ToyWithCount getMentionCounts(List<String[]> quotes, String toy) {
        int count = 0;
        int mentionedTime = 0;
        for (String[] words : quotes) {
            boolean occurred = false;
            for (String word : words) {
                if (word.toLowerCase().contains(toy.toLowerCase())) {
                    count++;
                    occurred = true;
                }
            }
            if (occurred) {
                mentionedTime++;
            }
        }
        return new ToyWithCount(toy, count, mentionedTime);
    }

    public static void main(String[] args) {
        String[] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        String[] quotes = {"Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
                        "The new Elmo dolls are super high quality",
                        "Expect the Elsa dolls to be very popular this year, Elsa!",
                        "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
                        "For parents of older kids, look into buying them a drone",
                        "Warcraft is slowly rising in popularity ahead of the holiday season"};
        List<String> topN = topNMentioned(6 ,2, Arrays.asList(toys), 6, Arrays.asList(quotes));
        System.out.println(topN);
    }
}
