package 剑指offer;

import leetcode.数据结构.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lzh
 * @date 2021/1/16 11:51
 */
public class _32_2_从上到下打印二叉树II {

    /**
     * 循环实现
     */
    public List<List<Integer>> levelOrder_cycle(TreeNode root) {

        List<List<Integer>> resList = new ArrayList<>();

        if(root == null) {
            return resList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            int size = queue.size();

            List<Integer> subList = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                TreeNode tmp = queue.remove();
                subList.add(tmp.val);

                if(tmp.left != null) {
                    queue.add(tmp.left);
                }

                if(tmp.right != null) {
                    queue.add(tmp.right);
                }
            }

            resList.add(subList);
        }

        return resList;
    }


    /**
     * 递归实现
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> resList = new ArrayList<>();

        recursive(root, resList, 0);

        return resList;
    }

    private void recursive(TreeNode root, List<List<Integer>> resList, int level) {

        if(root == null) {
            return;
        }

        if(resList.size() == level) {
            resList.add(new ArrayList<>());
        }

        resList.get(level).add(root.val);

        if(root.left != null) {
            recursive(root.left, resList, level + 1);
        }

        if(root.right != null) {
            recursive(root.right, resList, level + 1);
        }
    }
}
