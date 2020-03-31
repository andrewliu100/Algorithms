package geekspearls.amz.phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Position: SDE Intern
 *
 * Given two binary trees, they are said to be mirror equal to each other iff view of their leaves are mirrored image
 * of each other.
 *
 * SOLUTION
 *
 * Do in-order traversal of tree, and push each leaf node into an ArrayList(in java)/ vector(in c++)
 * Do the above process for both the trees
 * Answer is YES if one list is equal to other's reverse
 */
public class MirrorEqualTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isMirrorEqual(TreeNode n1, TreeNode n2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        preorder(n1, leaves1);
        preorder(n2, leaves2);
        return isMirrorEqual(leaves1, leaves2);
    }

    private boolean isMirrorEqual(List<Integer> l1, List<Integer> l2) {
        if (l1 == null && l2 ==null) {
            return true;
        }
        if (l1 == null || l2 == null) {
            return false;
        }
        if (l1.size() != l2.size()) {
            return false;
        }
        int size = l1.size();
        for (int i = 0; i < size; i++) {
            if (l1.get(i).equals(l2.get(size - i - 1))) {
                return false;
            }
        }
        return true;
    }

    private void preorder(TreeNode node, List<Integer> leaves) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                leaves.add(node.val);
            }
            preorder(node.left, leaves);
            preorder(node.right, leaves);
        }
    }
}
