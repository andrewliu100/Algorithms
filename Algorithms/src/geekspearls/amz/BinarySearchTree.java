package geekspearls.amz;

import geekspearls.tree.Node;

import java.util.List;

public class BinarySearchTree {

    /**
     * Construct a BST from unsorted array. Balancing is not required.
     */
    public Node constructBst(List<Integer> array) {
        if (array == null || array.isEmpty()) {
            return null;
        }
        Node root = null;
        for (Integer data : array) {
            root = insert(root, data);
        }
        return root;
    }

    /**
     * The distance between two given values in the BST.
     * Return -1 if any value does not exist.
     */
    public int distance(Node bst, int a, int b) {
        if (bst == null || search(bst, a) == null || search(bst, b) == null) {
            return -1;
        }
        // make sure a is not greater than b
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (a <= bst.value && bst.value <= b) {
            return distance(bst, a) + distance(bst, b);
        } else if (b < bst.value) {
            return distance(bst.left, a, b);
        } else {
            return distance(bst.right, a, b);
        }
    }

    private int distance(Node node, int data) {
        if (node == null) {
            return -1;
        }
        if (node.value == data) {
            return 0;
        }
        if (data < node.value) {
            return distance(node.left, data) + 1;
        } else {
            return distance(node.right, data) + 1;
        }
    }

    private Node search(Node node, int data) {
        if (node == null) {
            return null;
        }
        if (node.value == data) {
            return node;
        }
        if (data < node.value) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }

    private Node insert(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }
        if (data < node.value) {
            node.left = insert(node.left, data);
        } else {
            node.right = insert(node.right, data);
        }

        return node;
    }

}
