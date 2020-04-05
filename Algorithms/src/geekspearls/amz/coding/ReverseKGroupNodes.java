package geekspearls.amz.coding;

public class ReverseKGroupNodes {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode start = dummy;
        ListNode end = dummy;

        while (end != null) {
            int i = 0;
            while (i < k && end != null) {
                end = end.next;
                i++;
            }

            if (end != null) {
                start = reverseList(start, end);
                end = start;
            }
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode dummy, ListNode end) {
        if (dummy == null)
            return null;
        ListNode iter = dummy.next;
        while (dummy.next != end) {
            ListNode temp = iter.next;
            iter.next = temp.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }
        return iter;
    }

    public static void main(String[] args) {
        ReverseKGroupNodes rk = new ReverseKGroupNodes();
        ListNode head = new ListNode(1);
        ListNode iter = head;
        for (int i = 2; i <= 5; i++) {
            iter.next = new ListNode(i);
            iter = iter.next;
        }
        rk.reverseKGroup(head, 3);
    }
}
