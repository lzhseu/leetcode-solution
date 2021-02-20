package 剑指offer;

import leetcode.数据结构.树.TreeNode;

import java.util.LinkedList;

/**
 * @author lzh
 * @date 2021/2/15 15:05
 */
public class _37_序列化二叉树 {

    static class Codec {

        private static final String SEPARATOR = ",";
        private static final String NULL = "#";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {

            StringBuilder sb = new StringBuilder();
            serializePre(root, sb);
            return sb.toString();
        }

        private void serializePre(TreeNode root, StringBuilder sb) {

            //base case
            if(root == null) {
                sb.append(NULL).append(SEPARATOR);
                return;
            }

            sb.append(root.val).append(SEPARATOR);
            serializePre(root.left, sb);
            serializePre(root.right, sb);
        }


        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {

            if(data == null || data.length() == 0) {
                return null;
            }

            String[] dataArr = data.split(SEPARATOR);
            LinkedList<String> nodes = new LinkedList<>();

            for(String s : dataArr) {
                nodes.addLast(s);
            }

            return deserializePre(nodes);
        }

        private TreeNode deserializePre(LinkedList<String> nodes) {

            if(nodes.isEmpty()) {
                return null;
            }

            String data = nodes.removeFirst();

            if(NULL.equals(data)) {
                return null;
            }

            TreeNode node = new TreeNode(Integer.valueOf(data));

            node.left = deserializePre(nodes);
            node.right = deserializePre(nodes);

            return node;
        }
    }
}
