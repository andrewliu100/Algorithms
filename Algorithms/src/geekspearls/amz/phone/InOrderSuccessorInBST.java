package geekspearls.amz.phone;

public class InOrderSuccessorInBST {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode node) {
        // If node has a right subtree, the successor is left most node in the right subtree
        if (node.right != null) {
            TreeNode iter = node;
            while (iter != null) {
                iter = iter.left;
            }
            return iter;
        } else {
            // If node has no right subtree
            // the successor is one ancestor of the node. The node should be in the right subtree of
            // the ancestor's left subtree
            TreeNode succ = null;
            TreeNode iter = root;
            // traverse from root to search the node and record the ancestor
            while (iter != null) {
                if (node.val < iter.val) {
                    // left tree, update the succ
                    succ = iter;
                    iter = iter.left;
                } else if (node.val > iter.val) {
                    // right tree, keep searching
                    iter = iter.right;
                } else {
                    // found the node
                    break;
                }
            }
            return succ;
        }
    }
}
