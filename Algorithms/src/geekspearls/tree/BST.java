package geekspearls.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An implementation of binary search tree.
 *
 * Created by aliu on 27/07/16.
 */
public class BST {

    protected Node root;

    /**
     * Find the node of the given value.
     *
     * @param value The value to search
     * @return Node with the given value, null if not found
     */
    public Node find(int value) {
        Node p = root;
        while (p != null) {
            if (value == p.value) {
                return p;
            }
            if (value < p.value) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    /**
     * Recursion implementation of insert a new node to BST.
     *
     * @param node The node under which to insert
     * @param value The value of the node
     */
    public Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        return node;
    }

    /**
     * Iteration implementation of insert a new node to BST.
     *
     * @param value The value of the node.
     */
    public void insert(int value) {
        Node n = new Node(value);
        if (root == null) {
            root = n;
        } else {
            Node p = root;
            while (p != null) {
                if (n.value < p.value) {
                    if (p.left == null) {
                        p.left = n;
                        p = null;
                    } else {
                        p = p.left;
                    }
                } else {
                    if (p.right == null) {
                        p.right = n;
                        p = null;
                    } else {
                        p = p.right;
                    }
                }
            }
        }
    }

    public int distance(Node n, int a, int b) {
        int small = a > b ? b : a;
        int large = a > b ? a : b;

        if (small <= n.value && large >= n.value) {
            return level(n, small) + level(n, large);
        } else if (large < n.value) { // both are smaller than n.value
            return distance(n.left, a, b);
        } else { // both are bigger than n.value
            return distance(n.right, a, b);
        }
    }

    private int level(Node n, int x) {
        if (n == null) {
            throw new IllegalArgumentException(x + " is not found in the BST");
        }
        if (n.value == x) {
            return 0;
        }

        if (n.value > x) {
            return level(n.left, x) + 1;
        } else {
            return level(n.right, x) + 1;
        }
    }

    /**
     *
     * @param value
     */

    public void delete(int value) {
        Node n = root;
        Node p = null;
        boolean isLeft = false;
        while (n != null) {
            if (value == n.value) {
                break;
            }
            if (value < n.value) {
                p = n;
                n = p.left;
                isLeft = true;
            } else {
                p = n;
                n = p.right;
                isLeft = false;
            }
        }
        // Found the node to delete
        if (n != null) {
            // delete leaf
            if (n.left == null && n.right == null) {
                if (n == root) {
                    root = null;
                } else {
                    if (isLeft) {
                        p.left = null;
                    } else {
                        p.right = null;
                    }
                }
            } else if (n.left != null && n.right != null) { // has two children
                Node successor = findSuccessor(n);
                if (n == root) {
                    root = successor;
                } else {
                    if (isLeft) {
                        p.left = successor;
                    } else {
                        p.right = successor;
                    }
                }
            } else if (n.left != null) { // has single left child
                if (n == root) {
                    root = n.left;
                } else {
                    if (isLeft) {
                        p.left = n.left;
                    } else {
                        p.right = n.left;
                    }
                }
            } else if (n.right != null) { // has single right child
                if (n == root) {
                    root = n.right;
                } else {
                    if (isLeft) {
                        p.left = n.right;
                    } else {
                        p.right = n.right;
                    }
                }
            }
        }
    }

    /**
     * Find the smallest node greater than the given node.
     * And construct its left and right subtree.
     *
     * @param n
     * @return
     */
    private Node findSuccessor(Node n) {
        Node p = null;
        Node c = n.right;
        while (c.left != null) {
            p = c;
            c = c.left;
        }
        // n's right node doesn't have left node
        if (c == n.right) {
            c.left = n.left;
            return c;
        }
        // Give c its left and right sub trees
        p.left = c.right;
        c.left = n.left;
        c.right = n.right;
        return c;
    }

    /**
     * Find the largest node less than the given node.
     * And construct its left and right subtree.
     *
     * @param n
     * @return
     */
    private Node findPredecessor(Node n) {
        Node p = null;
        Node c = n.left;
        while (c.right != null) {
            p = c;
            c = c.right;
        }
        if (c == n.left) {
            c.right = n.right;
            return c;
        }
        p.right = c.left;
        c.left = n.left;
        c.right = n.right;
        return c;
    }

    public void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            System.out.print(n.value + ", ");
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
    }

    public void dfsInOrder() {
        inOrder(root);
        System.out.println();
    }

    public void dfsPreOrder() {
        preOrder(root);
        System.out.println();
    }

    public void dfsPostOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node n) {
        if (n != null) {
            postOrder(n.left);
            postOrder(n.right);
            System.out.print(n.value + ", ");
        }
    }

    private void preOrder(Node n) {
        if (n != null) {
            System.out.print(n.value + ", ");
            preOrder(n.left);
            preOrder(n.right);
        }
    }

    private void inOrder(Node n) {
        if (n != null) {
            inOrder(n.left);
            System.out.print(n.value + ", ");
            inOrder(n.right);
        }
    }

}
