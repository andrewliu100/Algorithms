package geekspearls.amz.OA;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPoint {

    static class ListNode {
        int val;
        ListNode next;
        ListNode random;

        ListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static ListNode deepCopy(ListNode head) {
        Map<ListNode, ListNode> nodeMap = new HashMap<>();
        // Copy all nodes
        ListNode iter = head;
        while (iter != null) {
            ListNode newNode = new ListNode(iter.val);
            nodeMap.put(iter, newNode);
            iter = iter.next;
        }

        // assign next and random pointers
        iter = head;
        while (iter != null) {
            nodeMap.get(iter).next = nodeMap.get(iter.next);
            nodeMap.get(iter).random = nodeMap.get(iter.random);
            iter = iter.next;
        }
        return nodeMap.get(head);
    }

}
