package 剑指offer;

import leetcode.数据结构.树.TreeNode;

/**
 * @author lzh
 * @date 2021/2/18 17:38
 */
public class _55_1_二叉树的深度 {

    /**
     * 写法一：自底向上
     */
    public int maxDepth(TreeNode root) {

        if(root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }


    /**
     * 写法二：自顶向下
     */
    public int maxDepth2(TreeNode root) {

        return getDepth(root, 0);
    }

    private int getDepth(TreeNode root, int depth) {

        if(root == null) {
            return depth;
        }

        depth++;
        int left = getDepth(root.left, depth);
        int right = getDepth(root.right, depth);

        return Math.max(left, right);
    }

}
