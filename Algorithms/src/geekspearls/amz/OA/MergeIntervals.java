package geekspearls.amz.OA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {

    public class Interval {
      int start;
      int end;
      Interval(int s, int e) { start = s; end = e; }
    }

    public int[][] merge(int[][] intervals) {
        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalList.add(new Interval(interval[0], interval[1]));
        }
        // sort by start point
        intervalList.sort(Comparator.comparingInt(i -> i.start));

        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval itv : intervalList) {
            if (merged.isEmpty()) {
                merged.add(itv);
            } else {
                Interval last = merged.getLast();
                if (last.end >= itv.start) {
                    // merge
                    last.end = Math.max(last.end, itv.end);
                } else {
                    merged.add(itv);
                }
            }
        }
        int[][] result = new int[merged.size()][2];
        for (int i = 0; i < merged.size(); i++) {
            result[i][0] = merged.get(i).start;
            result[i][1] = merged.get(i).end;
        }
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        System.out.println(Arrays.deepToString(mi.merge(new int[][] {{1,3},{2,6},{8,10},{15,18}})));
    }
}
