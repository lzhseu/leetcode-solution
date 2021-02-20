package 剑指offer;

import leetcode.数据结构.树.TreeNode;

import java.util.*;

/**
 * @author lzh
 * @date 2021/1/16 12:03
 */
public class _32_3_之字形打印二叉树 {

    /**
     * 循环遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> resList = new ArrayList<>();

        if(root == null) {
            return resList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        while(!queue.isEmpty()) {

            int size = queue.size();
            for(int i = 0; i < size; i++) {

                TreeNode tmp = queue.remove();

                if((level & 0x1) == 1) {
                    deque.addFirst(tmp.val);
                } else {
                    deque.addLast(tmp.val);
                }

                if(tmp.left != null) {
                    queue.add(tmp.left);
                }

                if(tmp.right != null) {
                    queue.add(tmp.right);
                }
            }

            resList.add(new ArrayList<>(deque));
            deque.clear();
            level++;
        }

        return resList;
    }


    /**
     * 这种写法要快一点
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {

        List<List<Integer>> resList = new ArrayList<>();

        if(root == null) {
            return resList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;

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

            if((level & 0x1) == 1) {
                Collections.reverse(subList);
            }

            resList.add(subList);
            level++;
        }

        return resList;
    }
}
