package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/9/27 9:01
 */
public class 二叉搜索树的最近公共祖先_235 {

    /**
     * 循环 7ms    39.4M
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode parent = root;
        while(true) {
            if(parent.val < p.val && parent.val < q.val) {
                parent = parent.right;
            } else if(p.val < parent.val && q.val < parent.val) {
                parent = parent.left;
            } else {
                return parent;
            }
        }
    }

    /**
     * 递归  7ms 39.7%  39.5M 66.46%
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {

        int rSUBp = root.val - p.val;
        int rSUBq = root.val - q.val;

        // 题目说了所有节点的值都是唯一，且 p q 为不同节点
        if(rSUBp * rSUBq <= 0) {
            return root;
        } else if(rSUBp > 0) {
            return helper(root.left, p, q);
        } else {
            return helper(root.right, p, q);
        }
    }
}
