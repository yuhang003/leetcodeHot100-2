package hard;

import entity.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 */
public class Problem_0124_MaxPathSum {

    public static void main(String[] args) {
        Problem_0124_MaxPathSum tmp = new Problem_0124_MaxPathSum();
        TreeNode root = new TreeNode(1, new TreeNode(2), null);
        System.out.println(tmp.maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        return process(root).maxPathSum;
    }

    public Info process(TreeNode node) {
        if (node == null) return null;

        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        int maxPathSumFromHead = node.val;
        if (leftInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, node.val + leftInfo.maxPathSumFromHead);
        }
        if (rightInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, node.val + rightInfo.maxPathSumFromHead);
        }

        int maxPathSum = node.val;
        if (leftInfo != null) {
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
        }
        if (rightInfo != null) {
            maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
        }
        maxPathSum = Math.max(maxPathSum, maxPathSumFromHead);
        if (leftInfo != null && rightInfo != null && leftInfo.maxPathSumFromHead > 0 && rightInfo.maxPathSumFromHead > 0) {
            maxPathSum = Math.max(maxPathSum, node.val + leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead);
        }

        return new Info(maxPathSum, maxPathSumFromHead);
    }

    class Info {
        int maxPathSum;
        int maxPathSumFromHead;

        public Info(int maxPathSum, int maxPathSumFromHead) {
            this.maxPathSum = maxPathSum;
            this.maxPathSumFromHead = maxPathSumFromHead;
        }
    }
}
