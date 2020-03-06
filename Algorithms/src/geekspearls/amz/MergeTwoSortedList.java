package geekspearls.amz;

public class MergeTwoSortedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * Recursion. Time: O(n), Space: O(n) stacktrace
     */
    public static ListNode mergeSortedList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeSortedList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeSortedList(l1, l2.next);
            return l2;
        }
    }

    /**
     * iteration. Time: O(n), Space: O(1)
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode result = l1.val < l2.val ? l1 : l2;
        ListNode n = result;
        ListNode cur1 = l1.val < l2.val ? l1.next : l1;
        ListNode cur2 = l2.val <= l1.val ? l2.next : l2;
        while (cur1 != null || cur2 != null) {
            if (cur1 != null && cur2 != null) {
                if (cur1.val < cur2.val) {
                    n.next = cur1;
                    cur1 = cur1.next;
                } else {
                    n.next = cur2;
                    cur2 = cur2.next;
                }
            } else if (cur1 != null) {
                n.next = cur1;
                cur1 = cur1.next;
            } else if (cur2 != null) {
                n.next = cur2;
                cur2 = cur2.next;
            }
            n = n.next;
        }
        return result;
    }
}
