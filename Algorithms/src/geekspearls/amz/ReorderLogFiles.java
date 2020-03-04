package geekspearls.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log
 * has at least one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are
 * ordered lexicographically ignoring identifier, with the identifier used in case of ties.
 * The digit-logs should be put in their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 */
public class ReorderLogFiles {

    static class LogEntry implements Comparable<LogEntry> {
        String id;
        String value;
        String original;

        LogEntry(String id, String value, String original) {
            this.id = id;
            this.value = value;
            this.original = original;
        }

        private boolean isDigitLog() {
            char firstChar = this.value.charAt(0);
            return firstChar >= '0' && firstChar <= '9';
        }

        @Override
        public int compareTo(LogEntry other) {
            if (!this.isDigitLog() && other.isDigitLog()) {
                return -1;
            }
            if (this.isDigitLog() && !other.isDigitLog()) {
                return 1;
            }
            if (!this.isDigitLog() && !other.isDigitLog()) {
                int letterCompare = this.value.compareTo(other.value);
                if (letterCompare == 0) {
                    return this.id.compareTo(other.id);
                }
                return letterCompare;
            }
            return 1;
        }
    }

    public static String[] reorderLogFiles(String[] logs) {
        TreeSet<LogEntry> sortedLogs = new TreeSet<>();
        for (String log : logs) {
            String[] logBits = log.split(" ", 2);
            sortedLogs.add(new LogEntry(logBits[0], logBits[1], log));
        }
        List<String> result = new ArrayList<>();
        for (LogEntry entry : sortedLogs) {
            result.add(entry.original);
        }
        return result.toArray(new String[result.size()]);
    }

    public static void main(String[] args) {
        String[] logs = {"a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"};
        String[] result = reorderLogFiles(logs);
        System.out.println(Arrays.asList(result));
    }

}
