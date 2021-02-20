package 剑指offer;

import leetcode.数据结构.树.TreeNode;

/**
 * @author lzh
 * @date 2021/2/20 20:45
 */
public class _68_1_二叉搜索树的最近公共祖先 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        int diffRP = root.val - p.val;
        int diffRQ = root.val - q.val;

        if(diffRP < 0 && diffRQ < 0) {

            return lowestCommonAncestor(root.right, p, q);

        } else if(diffRP > 0 && diffRQ > 0) {

            return lowestCommonAncestor(root.left, p, q);

        } else {
            return root;
        }
    }
}
