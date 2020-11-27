package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 15:11
 */
public class 二叉搜索树中的搜索_700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) {
            return null;
        }

        if(root.val == val) {
            return root;
        } else if(root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }
}
