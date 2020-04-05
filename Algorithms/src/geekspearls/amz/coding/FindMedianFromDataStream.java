package geekspearls.amz.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 */
public class FindMedianFromDataStream {

    ////////////////////////// SORTING //////////////////////////////////
    List<Integer> numList = new ArrayList<>();

    // O(nlog(n))
    public void addNum_(int num) {
        numList.add(num);
        Collections.sort(numList);
    }

    // O(1)
    public double getMedian_() {
        if (numList.size() % 2 == 1) {
            return (double)numList.get(numList.size() / 2);
        } else {
            return ((double)numList.get(numList.size() / 2 - 1) + (double)numList.get(numList.size() / 2)) / 2;
        }
    }


    ///////////////////////// TWO HEAPS //////////////////////////////////
    private PriorityQueue<Integer> largerHalf = new PriorityQueue<>(); // min heap
    private PriorityQueue<Integer> smallerHalf = new PriorityQueue<>((a, b) -> (b - a)); // max heap


    // O(logn)
    public void addNum(int num) {
        smallerHalf.add(num);
        // balancing
        largerHalf.add(smallerHalf.poll());
        if (smallerHalf.size() < largerHalf.size()) {
            smallerHalf.add(largerHalf.poll()); // move the smallest elem in larger half to smaller half
        }
    }

    // O(1)
    public double getMedian() {
        if (smallerHalf.size() == largerHalf.size()) {
            return ((double) smallerHalf.peek() + (double) largerHalf.peek()) / 2;
        } else {
            return (double)smallerHalf.peek();
        }
    }
}
