package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 9:33
 */
public class 相同的树_100 {

    /**
     * 方法1：二叉树序列化
     * 14ms 100%
     */
    public boolean isSameTree1(TreeNode p, TreeNode q) {

        return serializeTree(p).equals(serializeTree(q));
    }

    private String serializeTree(TreeNode p) {
        if(p == null) {
            return "#";
        }

        String left = serializeTree(p.left);
        String right = serializeTree(p.right);
        return left + "," + right + "," + p.val;
    }

    /**
     * 方法2：框架式递归
     * 0ms 100%
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if(p == null && q == null) {
            return true;
        } else if(p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
