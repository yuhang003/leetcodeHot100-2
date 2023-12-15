package medium;

import entity.TreeNode;

/**
 * 337. 打家劫舍 III
 */
public class Problem_0337_Rob {

    public int rob(TreeNode root) {
        int[] ans = process(root);
        return Math.max(ans[0], ans[1]);
    }

    public int[] process(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        // 拿到左右子节点的结果数组
        int[] left = process(node.left); // 左
        int[] right = process(node.right); // 右

        // 中
        // 偷父节点，子节点就不能偷，取0下标的结果
        int p1 = node.val + left[0] + right[0];

        // 不偷父节点，子节点就可以随便选择偷或者不偷
        int p2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{p2, p1};
    }
}
