package geekspearls.amz.OA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
 * A subtree of a tree is the node which have at least 1 child plus all its descendants. The average value of a subtree is the sum of its values, divided by the number of nodes.
 *
 * Example 1:
 *
 * Input:
 * 		 20
 * 	   /   \
 * 	 12     18
 *   /  |  \   / \
 * 11   2   3 15  8
 *
 * Output: 18
 * Explanation:
 * There are 3 nodes which have children in this tree:
 * 12 => (11 + 2 + 3 + 12) / 4 = 7
 * 18 => (18 + 15 + 8) / 3 = 13.67
 * 20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125
 *
 * 18 has the maximum average so output 18.
 */
public class MaxAvgSubtree {

    static class TreeNode {
        int val;
        List<TreeNode> children;

        TreeNode(int val) {
            this.val = val;
            children = new ArrayList<>();
        }

        void addChild(TreeNode child) {
            this.children.add(child);
        }
    }

    static class TreeNodeWithCountAndTotal {
        TreeNode node;
        double count; // the count of nodes in its children and itself
        double total; // the total value of its children and itself

        TreeNodeWithCountAndTotal(TreeNode node, double count, double total) {
            this.node = node;
            this.count = count;
            this.total = total;
        }
    }

    static double maxAvg = Double.MIN_VALUE;
    static TreeNode maxSubtree = null;

    /**
     * Recursion. The avg of a subtree =
     * (the value of the node + sum(value of all its children)) / (1 + sum(node count of all its children))
     */
    public static TreeNode maxAvgSubtree(TreeNode root) {
        if (root == null || root.children.isEmpty()) {
            return null;
        }
        calc(root);
        return maxSubtree;
    }

    private static TreeNodeWithCountAndTotal calc(TreeNode node) {
        if (node == null) {
            return new TreeNodeWithCountAndTotal(null, 0, 0);
        }
        System.out.println("process node: " + node.val);
        double totalVal = node.val;
        double count = 1;
        for (TreeNode child : node.children) {
            TreeNodeWithCountAndTotal childResult = calc(child);
            totalVal += childResult.total;
            count += childResult.count;
//            System.out.println("child->" + child.val + " " + node.val + "->process node: totalVal:" + totalVal);
//            System.out.println("child->" + child.val + " " + node.val + "->process node: count:" + count);
        }
        // calc the avg
//        System.out.println(node.val + "->process node: totalVal:" + totalVal);
//        System.out.println(node.val + "->process node: count:" + count);
        double avg = totalVal / count;
        // Only update for subtree, ignore leaves
        if (count > 1 && avg > maxAvg) {
            maxAvg = avg;
            maxSubtree = node;
//            System.out.println("max avg: " + maxAvg);
//            System.out.println("max node: " + maxSubtree.val);
        }
        return new TreeNodeWithCountAndTotal(node, count, totalVal);
    }

    public static void main(String[] args) {
        // Input:
        //              20
        //            /   \
        //          12     18
        //       /  |  \   / \
        //     11   2   3 15  8
        TreeNode left = new TreeNode(12);
        left.children = Arrays.asList(new TreeNode(11), new TreeNode(2), new TreeNode(3));

        TreeNode right = new TreeNode(18);
        right.children = Arrays.asList(new TreeNode(15), new TreeNode(8));

        TreeNode root = new TreeNode(20);
        root.children = Arrays.asList(left, right);

        System.out.println(maxAvgSubtree(root).val);

    }
}
