package leetcode.数据结构.树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lzh
 * @date 2020/11/12 11:20
 */
public class 二叉树的层序遍历_102 {

    /**
     * 方法1：迭代
     * 1ms 93.01%    38.7M 84.52%
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {

        List<List<Integer>> resList = new ArrayList<>();
        if(root == null) {
            return resList;
        }

        List<Integer> subList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {

            int size = queue.size();

            for(int i = 0; i < size; i++) {
                TreeNode tmpNode = queue.poll();

                subList.add(tmpNode.val);

                if(tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                }
                if(tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                }

            }
            resList.add(new ArrayList<>(subList));
            subList.clear();
        }

        return resList;

    }


    /**
     * 方法2：递归
     * 1ms    38.9M 68.53%
     */
    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null) {
            return resList;
        }

        recursive(root, 0);

        return resList;

    }

    private void recursive(TreeNode root, int level) {

        if(resList.size() == level) {
            resList.add(new ArrayList<>());
        }

        resList.get(level).add(root.val);

        if(root.left != null) {
            recursive(root.left, level + 1);
        }

        if(root.right != null) {
            recursive(root.right, level + 1);
        }
    }
}
