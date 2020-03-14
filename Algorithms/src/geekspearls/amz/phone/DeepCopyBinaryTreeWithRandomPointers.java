package geekspearls.amz.phone;

import java.util.HashMap;
import java.util.Map;

/**
 * I was asked about a problem to copy a binary tree with random pointers. For example:
 *
 *     old:                                copied
 * 	1                                     1
 *    / \             ------>               /  \
 *   2---3                                 2---3
 * node 2 has a random pointer to node 3, and we will retain such structure in our copied result.
 */
public class DeepCopyBinaryTreeWithRandomPointers {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode random;

        TreeNode(int val) {
            this.val = val;
        }
    }

    Map<TreeNode, TreeNode> originalToCopy = new HashMap<>();

    public TreeNode deepCopy(TreeNode root) {
        // copy each node
        TreeNode newRoot = preOrderCopy(root);
        // assign random point
        preOrderRandomPointer(root);

        return newRoot;
    }

    private void preOrderRandomPointer(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode newNode = originalToCopy.get(node);
        newNode.random = originalToCopy.get(node.random);
        preOrderRandomPointer(node.left);
        preOrderRandomPointer(node.right);
    }

    private TreeNode preOrderCopy(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode newNode = new TreeNode(node.val);
        originalToCopy.put(node, newNode);
        newNode.left = preOrderCopy(node.left);
        newNode.right = preOrderCopy(node.right);
        return newNode;
    }

}
