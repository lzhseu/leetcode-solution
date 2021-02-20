package 剑指offer;

import leetcode.数据结构.树.TreeNode;

/**
 * @author lzh
 * @date 2021/2/19 9:49
 */
public class _55_2_平衡二叉树 {

    public boolean isBalanced(TreeNode root) {

        return height(root) > -1;
    }

    private int height(TreeNode root) {

        if(root == null) {
            return 0;
        }

        int left = height(root.left);
        int right = height(root.right);

        if(left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }
}
