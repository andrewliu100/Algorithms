package geekspearls.tree;

/**
 * Created by aliu on 9/08/16.
 */
public class AVLTree {

    int height(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
    }

    /**
     *    x            y
     *   / \          / \
     *  y  T3  ->    T1 x
     * / \             / \
     *T1 T2           T2 T3
     *
     *
     * @param node
     */
    Node rightRotate(Node node) {
        Node x = node;
        Node y = node.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    /**
     *    x              y
     *   / \            / \
     *  T1 y     ->    x  T3
     *    / \         / \
     *   T2 T3       T1 T2
     * @param node
     */
    Node leftRotate(Node node) {
        Node x = node;
        Node y = node.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    public Node insert(Node node, int value) {
        // 1. perform standard BST insert
        if (node == null) {
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        // 2. get balance of the tree
        int balance = height(node.left) - height(node.right);

        // 3. Adjust the balance of the tree
        if (balance > 1) {
            // left left case
            if (value < node.left.value) {
                return rightRotate(node);
            }
            // left right case
            if (value > node.left.value) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balance < -1) {
            //right right case
            if (value > node.right.value) {
                return leftRotate(node);
            }
            //right left case
            if (value < node.right.value) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }


}
