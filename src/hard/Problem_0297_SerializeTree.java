package hard;

import entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 */
public class Problem_0297_SerializeTree {

    class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            StringBuilder builder = new StringBuilder();
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();

                if (node != null) {
                    builder.append(node.val).append(",");
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    builder.append("None,");
                }
            }

            return builder.deleteCharAt(builder.length() - 1).toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if ("None".equals(data)) return null;

            String[] dataArr = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(dataArr[0]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            int index = 1;
            while (index < dataArr.length - 2 && !queue.isEmpty()) {
                TreeNode node = queue.poll();
                String leftStr = dataArr[index++];
                String rightStr = dataArr[index++];

                if (!"None".equals(leftStr)) {
                    node.left = new TreeNode(Integer.parseInt(leftStr));
                    queue.add(node.left);
                }

                if (!"None".equals(rightStr)) {
                    node.right = new TreeNode(Integer.parseInt(rightStr));
                    queue.add(node.right);
                }
            }

            return root;
        }
    }
}
