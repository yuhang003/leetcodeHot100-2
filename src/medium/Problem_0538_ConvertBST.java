package medium;

import entity.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 538. 把二叉搜索树转换为累加树
 */
public class Problem_0538_ConvertBST {

    public static void main(String[] args) {
        Problem_0538_ConvertBST tmp = new Problem_0538_ConvertBST();
        TreeNode root = new TreeNode(4, new TreeNode(1, new TreeNode(0),
                new TreeNode(2, null, new TreeNode(3))),
                new TreeNode(6, new TreeNode(5), new TreeNode(7, null, new TreeNode(8))));
        TreeNode node = tmp.convertBST(root);
        System.out.println(node);
    }

    public TreeNode convertBST(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        int sum = 0;
        TreeNode cur = root;

        while (cur != null || !deque.isEmpty()) {
            if (cur != null) {
                deque.push(cur);
                cur = cur.right;
            } else {
                cur = deque.pop();
                sum += cur.val;
                cur.val = sum;

                cur = cur.left;
            }
        }

        return root;
    }
}
