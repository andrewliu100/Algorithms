package geekspearls.amz.coding;

/**
 * Got an coding interview @ amazon for SDE2 role at Amazon for Business team, Austin.
 *
 * I got asked to:
 *
 * Write a Person class which is basically a binary tree
 * https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
 * https://www.geeksforgeeks.org/check-if-two-trees-are-mirror/
 * Questions 2 and 3 were based on the Person class written on (1).
 */
public class IdenticalAndSymmetricTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isIdentical(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        return tree1.val == tree2.val &&
                        isIdentical(tree1.left, tree2.left) &&
                        isIdentical(tree1.right, tree2.right);
    }

    public boolean isSymmetric(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null && tree2 == null) {
            return true;
        }
        if (tree1 == null || tree2 == null) {
            return false;
        }
        return tree1.val == tree2.val &&
                        isSymmetric(tree1.left, tree2.right) &&
                        isSymmetric(tree1.right, tree2.left);
    }
}
