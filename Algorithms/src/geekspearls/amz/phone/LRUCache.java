package geekspearls.amz.phone;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * The cache is initialized with a positive capacity.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache(2)
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 */
public class LRUCache {

    class ListNode {
        int key;
        int value;
        ListNode pre;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, ListNode> cache;

    // Least used entry is at the tail. Most recent used entry is moved to the head
    private ListNode head;
    private ListNode tail;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new ListNode(-1, -1);
        this.tail = new ListNode(-1, -1);
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    private void deleteNode(ListNode node) {
        ListNode pre = node.pre;
        ListNode next = node.next;
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
    }

    private void addToHead(ListNode node) {
        ListNode next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }

    private void moveToHead(ListNode node) {
        deleteNode(node);
        addToHead(node);
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        ListNode node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            ListNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // check capacity
            int size = cache.size();
            if (size >= capacity) {
                // remove the least used entry which is at the tail
                ListNode lru = tail.pre;
                deleteNode(lru);
                cache.remove(lru.key);
            }
            ListNode newNode = new ListNode(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
