package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/9/23 14:35
 */
public class 合并二叉树_617 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode root = helper(t1, t2);
        return root;
    }


    /**
     * 2ms  7.28%
     */
    private TreeNode helper(TreeNode t1, TreeNode t2) {

        if(t1 == null && t2 == null) {
            return null;
        }

        TreeNode left1 = t1 == null ? null : t1.left;
        TreeNode left2 = t2 == null ? null : t2.left;
        TreeNode newLeft = helper(left1, left2);

        TreeNode rht1 = t1 == null ? null : t1.right;
        TreeNode rht2 = t2 == null ? null : t2.right;
        TreeNode newRight = helper(rht1, rht2);

        int v1 = t1 == null ? 0 : t1.val;
        int v2 = t2 == null ? 0 : t2.val;
        TreeNode root = new TreeNode(v1 + v2);
        root.left = newLeft;
        root.right = newRight;

        return root;
    }


    /**
     * 1ms
     */
    private TreeNode helper2(TreeNode t1, TreeNode t2) {

        if(t1 == null) {
            return t2;
        } else if(t2 == null) {
            return t1;
        }

        TreeNode res = new TreeNode(t1.val + t2.val);
        res.left = helper(t1.left, t2.left);
        res.right = helper(t1.right, t2.right);
        return res;
    }
}
