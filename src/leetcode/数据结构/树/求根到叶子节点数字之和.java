package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/19 11:10
 */
public class 求根到叶子节点数字之和 {

    public int sumNumbers(TreeNode root) {
        return traverse(root, 0);
    }

    private int traverse(TreeNode root, int i) {

        // base case
        if(root == null) {
            return 0;
        }

        int temp = 10 * i + root.val;
        if(root.left == null && root.right == null) {
            return temp;
        }

        return traverse(root.left, temp) + traverse(root.right, temp);
    }
}
