package 剑指offer;

import leetcode.数据结构.树.TreeNode;

/**
 * @author lzh
 * @date 2021/2/18 17:34
 */
public class _54_二叉搜索树的第k大节点 {

    private int result;
    private int k;

    public int kthLargest(TreeNode root, int k) {

        this.k = k;
        helper(root);
        return result;
    }

    private void helper(TreeNode root) {

        if(root == null) {
            return;
        }

        helper(root.right);

        // 这一步判断是为了：提前终止
        if(k == 0) {
            return;
        }
        if(--k == 0) {
            result = root.val;
            return;
        }

        helper(root.left);
    }
}
