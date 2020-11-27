package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 10:29
 */
public class 恢复二叉搜索树_99 {

    private TreeNode s = null, t = null;
    private TreeNode prev = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        traverse(root);
        int tmp = s.val;
        s.val = t.val;
        t.val = tmp;
    }

    private void traverse(TreeNode node) {

        if(node == null) {
            return;
        }

        traverse(node.left);

        if(node.val < prev.val) {
            s = s == null ? prev : s;
            t = node;
        }
        prev = node;

        traverse(node.right);
    }

}
