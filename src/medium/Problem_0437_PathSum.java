package medium;

import entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 */
public class Problem_0437_PathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10, new TreeNode(5, new TreeNode(3, new TreeNode(3), new TreeNode(-2)), new TreeNode(2, null, new TreeNode(1)))
                , new TreeNode(-3, null, new TreeNode(11)));
        int targetSum = 8;

        Problem_0437_PathSum tmp = new Problem_0437_PathSum();
        System.out.println(tmp.pathSum(root, targetSum));
    }

    // 递归，非最优解法，没有记录中间过程
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;

        int ans = 0;
        ans += process(root, targetSum);
        ans += pathSum(root.left, targetSum);
        ans += pathSum(root.right, targetSum);
        return ans;
    }

    private int process(TreeNode node, long rest) {
        if (node == null) return 0;

        int ans = 0;
        int val = node.val;
        if (rest - val == 0) ans++;

        ans += process(node.left, rest - val);
        ans += process(node.right, rest - val);
        return ans;
    }

    // 最优解，记录中间经过的节点的和，所以每个节点只用遍历一次即可
    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0L, 1);

        return process2(root, targetSum, 0, preSumMap);
    }

    public int process2(TreeNode root, int target, long preAll, Map<Long, Integer> preSumMap) {
        if (root == null) return 0;

        int ans = 0;
        long all = preAll + root.val;
        if (preSumMap.containsKey(all - target)) {
            ans = preSumMap.get(all - target);
        }
        preSumMap.put(all, preSumMap.getOrDefault(all, 0) + 1);

        ans += process2(root.left, target, all, preSumMap);
        ans += process2(root.right, target, all, preSumMap);

        preSumMap.put(all, preSumMap.get(all) - 1);
        return ans;
    }
}
