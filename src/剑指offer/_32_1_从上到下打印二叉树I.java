package 剑指offer;

import leetcode.数据结构.树.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lzh
 * @date 2021/1/16 11:40
 */
public class _32_1_从上到下打印二叉树I {

    /**
     * 循环实现
     */
    public int[] levelOrder(TreeNode root) {

        if(root == null) {
            return new int[] {};
        }

        List<Integer> resList = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {

            TreeNode firstNode = queue.remove();
            resList.add(firstNode.val);

            if(firstNode.left != null) {
                queue.add(firstNode.left);
            }

            if(firstNode.right != null) {
                queue.add(firstNode.right);
            }

        }

        int[] result = new int[resList.size()];
        for(int i = 0; i < resList.size(); i++) {
            result[i] = resList.get(i);
        }

        return result;
    }


}
