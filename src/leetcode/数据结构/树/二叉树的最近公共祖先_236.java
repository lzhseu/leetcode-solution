package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 21:30
 */
public class 二叉树的最近公共祖先_236 {

    /**
     * 框架式编码
     * 8ms 56.23%    40.7M 72.91%
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null) {
            return null;
        }
        if(root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null) {
            return root;
        } else if(left == null && right == null) {
            return null;
        } else {
            return left == null ? right : left;
        }
    }
}
