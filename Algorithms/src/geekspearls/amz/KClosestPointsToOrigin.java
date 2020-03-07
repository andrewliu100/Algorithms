package geekspearls.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 *
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 * Example 2:
 *
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 */
public class KClosestPointsToOrigin {

    static class Point implements Comparable<Point> {
        int x;
        int y;
        double distance;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = Math.sqrt(x * x +y * y);
        }

        public int compareTo(Point that) {
            return Double.compare(this.distance, that.distance);
        }
    }

    /**
     * Use min heap. Time complexity of heap: add() and poll(): O(logn)
     *
     * Time: O(nlogn)
     * Space: O(n)
     */
    public static List<List<Integer>> kClosest(List<List<Integer>> points, int k) {
        // Min heap
        PriorityQueue<Point> minHeap = new PriorityQueue<>();
        for (List<Integer> coordinate : points) {
            minHeap.add(new Point(coordinate.get(0), coordinate.get(1)));
        }
        // get k points
        List<List<Integer>> kPoints = new ArrayList<>();
        while (kPoints.size() < k && !minHeap.isEmpty()) {
            Point p = minHeap.poll();
            kPoints.add(Arrays.asList(p.x, p.y));
        }
        return kPoints;
    }

    public static void main(String[] args) {
        List<List<Integer>> points = Arrays.asList(
                Arrays.asList(3, 3),
                Arrays.asList(5, -1),
                Arrays.asList(-2, 4)
        );
        System.out.println(kClosest(points, 2));

    }
}
