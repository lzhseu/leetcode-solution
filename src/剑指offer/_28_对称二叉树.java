package 剑指offer;

import leetcode.数据结构.树.TreeNode;

/**
 * @author lzh
 * @date 2021/1/15 19:52
 */
public class _28_对称二叉树 {

    public boolean isSymmetric(TreeNode root) {

        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {

        if(root1 == null && root2 == null) {
            return true;
        } else if(root1 == null || root2 == null) {
            return false;
        }

        return root1.val == root2.val &&
                isSymmetric(root1.left, root2.right) &&
                isSymmetric(root1.right, root2.left);
    }
}
