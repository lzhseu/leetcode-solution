package leetcode.数据结构.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lzh
 * @date 2020/11/18 19:58
 */
public class 二叉树的序列化与反序列化_297 {

    private static final String NULL = "#";
    private static final String SEP = ",";

    /**
     * 前序遍历进行序列化与反序列化
     */
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) {
            return null;
        }
        String[] dataArr = data.split(SEP);
        LinkedList<String> nodes = new LinkedList<>();
        for(String s : dataArr) {
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    public TreeNode deserialize(LinkedList<String> nodes) {

        if(nodes.isEmpty()) {
            return null;
        }

        String data = nodes.removeFirst();

        if(NULL.equals(data)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(data));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }

    //////////////////////////////////////////////////////////////////////////

    /**
     * 后序遍历序列化与反序列化（只写出辅助函数，调用与前序同）
     */

    public void serializePost(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        serializePost(root.left, sb);
        serializePost(root.right, sb);
        sb.append(root.val).append(SEP);
    }

    public TreeNode deserializePost(LinkedList<String> nodes) {

        if(nodes.isEmpty()) {
            return null;
        }

        String data = nodes.removeLast();

        if(NULL.equals(data)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(data));
        root.right = deserializePost(nodes);
        root.left = deserializePost(nodes);

        return root;
    }
    //////////////////////////////////////////////////////////////////////////

    /**
     * 中序遍历序列化【注：中序遍历无法反序列化】
     */
    public void serializeIn(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        serializeIn(root.left, sb);
        sb.append(root.val).append(SEP);
        serializeIn(root.right, sb);
    }

    /**
     * 层级遍历序列化和反序列化（相对来说比较慢）
     */
    public String serializeLevel(TreeNode root) {
        if(root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        // 注意这里要使用 LinkedList，因为它的实现可以加入 null
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            TreeNode curr = queue.poll();

            if(curr == null) {
                sb.append(NULL).append(SEP);
                continue;
            }

            sb.append(curr.val).append(SEP);

            queue.offer(curr.left);
            queue.offer(curr.right);
        }

        return sb.toString();
    }


    public TreeNode deserializeLevel(String data) {
        if(data == null || data.length() == 0) {
            return null;
        }
        String[] nodes = data.split(SEP);

        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for(int i = 1; i < nodes.length; ) {
            // 队列中存的都是父节点
            TreeNode parent = queue.poll();
            // 父节点对应的左侧子节点的值
            String leftVal = nodes[i++];
            if(NULL.equals(leftVal)) {
                parent.left = null;
            } else {
                parent.left = new TreeNode(Integer.valueOf(leftVal));
                queue.offer(parent.left);
            }
            // 父节点对应的右侧子节点的值
            String rightVal = nodes[i++];
            if(NULL.equals(rightVal)) {
                parent.right = null;
            } else {
                parent.right = new TreeNode(Integer.valueOf(rightVal));
                queue.offer(parent.right);
            }
        }
        return root;
    }
}
