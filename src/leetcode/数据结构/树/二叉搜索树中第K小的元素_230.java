package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 11:10
 */
public class 二叉搜索树中第K小的元素_230 {

    public int kthSmallest(TreeNode root, int k) {

        traverse(root, k);
        return res;
    }

    int res;
    int rank = 0;
    private void traverse(TreeNode root, int k) {
        if(root == null){
            return;
        }
        traverse(root.left, k);

        if(++rank == k) {
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }
}
