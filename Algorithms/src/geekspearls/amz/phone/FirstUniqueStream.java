package geekspearls.amz.phone;

import java.util.HashMap;
import java.util.Map;

/**
 * Adds integer num to a stream. Get first unique integer in the stream if found else return null
 */
public class FirstUniqueStream {

    /**
     * A double linked list to store only the unique number. The earliest number is at the head of the list. New node
     * is always added at the tail of the list.
     */
    class DLinkedNode {
        int value;
        DLinkedNode pre;
        DLinkedNode next;

        DLinkedNode(int value) {
            this.value = value;
        }
    }

    Map<Integer, DLinkedNode> map;
    DLinkedNode head;
    DLinkedNode tail;

    public FirstUniqueStream() {
        head = new DLinkedNode(-1);
        tail = new DLinkedNode(-1);
        head.next = tail;
        tail.pre = head;
        this.map = new HashMap<>();
    }

    private void deleteNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
    }

    private void addToTail(DLinkedNode node) {
        DLinkedNode preTail = tail.pre;
        preTail.next = node;
        node.pre = preTail;
        node.next = tail;
        tail.pre = node;
    }

    /**
     * Adds integer num to a stream of integers.
     *
     * O(1)
     */
    public void add(int num) {
        if (map.containsKey(num)) {
            // duplicate in the stream, delete the node from the DLL
            DLinkedNode node = map.get(num);
            deleteNode(node);
        } else {
            // Unique number, add to the tail
            DLinkedNode node = new DLinkedNode(num);
            map.put(num, node);
            addToTail(node);
        }
    }

    /**
     *  Returns the first unique integer in the stream if found else return null.
     *
     *  O(1)
     */
    public Integer getFirstUnique() {
        if (head.next == tail) {
            return null;
        }
        return head.next.value;
    }

    public static void main(String[] args) {
        FirstUniqueStream s = new FirstUniqueStream();
        s.add(2);
        assert s.getFirstUnique() == 2;
        s.add(2);
        assert s.getFirstUnique() == null;
        s.add(3);
        assert s.getFirstUnique() == 3;
        s.add(4);
        assert s.getFirstUnique() == 3;
        s.add(3);
        assert s.getFirstUnique() == 4;
    }
}
