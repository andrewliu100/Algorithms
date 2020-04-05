package geekspearls.amz.coding;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Virtual interview question:
 * Given a linkedlist where horizontal and vertical elements are sorted.
 * Sort them into a single linear linked list
 *
 * 2 10 17 23 25...
 * |  |  |  |  |....
 * 3 12 34  27 35
 * |  |  |  |  |...
 * 25 34....
 */
public class MergeKSortedLists {

    class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    public Node mergeSortedLists(List<Node> linkedLists) {
        if (linkedLists == null || linkedLists.size() == 0) {
            return null;
        }
        Node head = new Node(0); // dummy head
        Node point = head;
        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2) -> n1.val -n2.val);
        for (Node node : linkedLists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            point.next = node;
            point = node;
            if (node.next != null) {
                minHeap.add(node.next);
            }
        }
        return head.next;
    }
}
