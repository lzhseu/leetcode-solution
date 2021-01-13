package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2021/1/8 9:46
 */
public class 路径总和_112 {

    public boolean hasPathSum(TreeNode root, int sum) {
        return traverse(root, sum);
    }

    private boolean traverse(TreeNode root, int sum) {

        if(root == null) {
            return false;
        }

        sum -= root.val;

        if(root.left == null && root.right == null) {
            return sum == 0;
        }

        return traverse(root.left, sum) || traverse(root.right, sum);

    }
}
