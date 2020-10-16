package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/9/16 17:07
 */
public class 翻转二叉树_226 {

    public TreeNode invertTree(TreeNode root) {
        recursive(root);
        return root;
    }

    private void recursive(TreeNode root) {

        if(root == null) {
            return;
        }

        TreeNode tmpNode = root.left;
        root.left = root.right;
        root.right = tmpNode;

        recursive(root.left);
        recursive(root.right);
    }
}
