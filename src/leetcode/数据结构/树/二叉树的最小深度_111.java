package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/8/21 10:12
 */
public class 二叉树的最小深度_111 {

    public int minDepth(TreeNode root) {
        return getDepth(root, 0);
    }

    private int getDepth(TreeNode root, int depth) {
        if(root == null) {
            return depth;
        } else if(root.left == null) {
            return getDepth(root.right, depth) + 1;
        } else if(root.right == null) {
            return getDepth(root.left, depth) + 1;
        }
        depth++;
        return Math.min(getDepth(root.left, depth), getDepth(root.right, depth));
    }
}
