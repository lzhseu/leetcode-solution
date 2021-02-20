package 剑指offer;

import leetcode.数据结构.树.TreeNode;

/**
 * @author lzh
 * @date 2021/1/15 19:45
 */
public class _27_二叉树的镜像 {

    public TreeNode mirrorTree(TreeNode root) {

        if(root == null) {
            return null;
        }

        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);

        root.left = right;
        root.right = left;

        return root;

    }
}
