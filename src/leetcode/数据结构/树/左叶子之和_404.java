package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/9/19 15:55
 */
public class 左叶子之和_404 {

    private int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) {
            return 0;
        }
        recursive(root);
        return sum;
    }

    private void recursive(TreeNode root) {

        if(root.left == null && root.right == null) {
            return;
        }

        if(root.left != null) {
            recursive(root.left);
            if(root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            }
        }

        if(root.right != null) {
            recursive(root.right);
        }
    }
}
