package medium;

import entity.TreeNode;

public class Problem_0114_Flatten {

    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                // 找到左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // 将右子树插入到最右节点
                // 因为先序遍历来说，左子树的最右节点一定是右子树的上一个节点
                pre.right = root.right;
                root.right = root.left;
                // 将左子树置空
                root.left = null;
                root = root.right;
            }
        }
    }
}
