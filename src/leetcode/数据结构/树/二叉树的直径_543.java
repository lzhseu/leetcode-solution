package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/12/26 19:42
 */
public class 二叉树的直径_543 {

    private int diameter = 0;


    public int diameterOfBinaryTree(TreeNode root) {
        recursive(root);
        return diameter;
    }

    private int recursive(TreeNode root) {

        // base case
        if(root == null) {
            return 0;
        }

        int left = recursive(root.left);
        int right = recursive(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;

    }
}
