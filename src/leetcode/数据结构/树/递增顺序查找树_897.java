package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 9:12
 */
public class 递增顺序查找树_897 {

    /**
     * 0ms 100%    35.9M 86%
     */
    TreeNode newRoot = new TreeNode(-1);
    TreeNode prev = newRoot;

    public TreeNode increasingBST(TreeNode root) {

        helper(root);
        newRoot = newRoot.right;
        return newRoot;
    }

    private void helper(TreeNode root) {
        if(root == null) {
            return;
        }

        helper(root.left);

        // 中序遍历框架位置
        TreeNode ans = new TreeNode(root.val);
        prev.right = ans;
        prev = ans;
        /////////////////

        helper(root.right);
    }
}
