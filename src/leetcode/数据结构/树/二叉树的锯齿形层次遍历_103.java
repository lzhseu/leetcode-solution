package leetcode.数据结构.树;

import java.util.*;

/**
 * @author lzh
 * @date 2020/11/18 18:30
 */
public class 二叉树的锯齿形层次遍历_103 {

    /**
     * 迭代法
     * 1ms 98.55%    38.8M 43.49%
     */
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {

        // 迭代法
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> resList = new ArrayList<>();

        if(root == null) {
            return resList;
        }

        boolean revertFlag = false;
        deque.addLast(root);

        while(!deque.isEmpty()) {

            int size = deque.size();
            List<Integer> subList = new ArrayList<>();

            for(int i = 0; i < size; i++) {

                TreeNode node = deque.removeFirst();
                subList.add(node.val);

                if(node.left != null) {
                    deque.addLast(node.left);
                }
                if(node.right != null) {
                    deque.addLast(node.right);
                }

            }

            if(revertFlag) {
                Collections.reverse(subList);
            }
            resList.add(subList);

            revertFlag = !revertFlag;
        }

        return resList;
    }

    /**
     * 递归法
     * 1ms    38.4M 71.3%
     */
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {

        // 递归法
        List<List<Integer>> resList = new ArrayList<>();

        traverse(root, 0, resList);

        return resList;
    }

    private void traverse(TreeNode root, int level, List<List<Integer>> resList) {

        if(root == null) {
            return;
        }

        if(resList.size() == level) {
            resList.add(new ArrayList<>());
        }

        if(level % 2 == 0) {
            resList.get(level).add(root.val);
        } else {
            resList.get(level).add(0, root.val);
        }

        traverse(root.left, level + 1, resList);
        traverse(root.right, level + 1, resList);
    }
}
