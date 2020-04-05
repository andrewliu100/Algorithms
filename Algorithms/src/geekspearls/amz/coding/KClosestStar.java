package geekspearls.amz.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You're given a billion stars and their points in (x,y,z) in the sky. Return the 100 closest points
 */
public class KClosestStar {

    static class Star implements Comparable<Star> {
        double x, y, z;

        double distance() {
            return Math.sqrt(x*x + y*y + z*z);
        }

        public int compareTo(Star that) {
            return Double.compare(this.distance(), that.distance());
        }
    }

    /**
     * Time: O(nlogk) n is the number of stars
     * Space: O(k)
     */
    public static List<Star> findKClosest(Iterator<Star> stars, int k) {
        PriorityQueue<Star> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        while (stars.hasNext()) {
            Star star = stars.next();
            maxHeap.add(star);
            if (maxHeap.size() == k + 1) {
                maxHeap.poll();
            }
        }
        List<Star> kClosest = new ArrayList<>();
        while (kClosest.size() < k) {
            kClosest.add(maxHeap.peek());
        }
        return kClosest;
    }
}
