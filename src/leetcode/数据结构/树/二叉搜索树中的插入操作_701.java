package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 15:37
 */
public class 二叉搜索树中的插入操作_701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {


        if(root == null) {
            return new TreeNode(val);
        }

        // 要插入，必须先找到插入位置
        // 题目保证了新值和原始 BST 中的任意节点值都不同
        if(root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }
}
