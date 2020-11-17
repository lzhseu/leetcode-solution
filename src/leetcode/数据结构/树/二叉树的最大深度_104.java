package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/8/21 10:24
 */
public class 二叉树的最大深度_104 {

    /**
     * 方法一：自顶向下递归
     */
    public int maxDepth1(TreeNode root) {
        return getDepth(root, 0);
    }

    private int getDepth(TreeNode root, int depth) {
        if(root == null) {
            return depth;
        }
        depth++;
        return Math.max(getDepth(root.left, depth), getDepth(root.right, depth));
    }

    /**
     * 方法二：自底向上递归
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
