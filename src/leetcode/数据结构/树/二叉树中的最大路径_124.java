package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/17 16:34
 */
public class 二叉树中的最大路径_124 {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        traverse(root);
        return max;
    }

    private int traverse(TreeNode root) {

        if(root == null) {
            return 0;
        }

        int left = Math.max(0, traverse(root.left));
        int right = Math.max(0, traverse(root.right));

        max = Math.max(max, root.val + left + right);

        return Math.max(left, right) + root.val;
    }
}
