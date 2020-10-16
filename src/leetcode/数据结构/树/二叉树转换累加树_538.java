package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/9/21 16:40
 */
public class 二叉树转换累加树_538 {

    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if(root == null) {
            return root;
        }

        recursive(root);
        return root;
    }

    private void recursive(TreeNode root) {

        if(root == null) {
            return;
        }

        recursive(root.right);
        sum += root.val;
        root.val = sum;
        recursive(root.left);

    }
}
