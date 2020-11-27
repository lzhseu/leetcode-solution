package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/24 9:40
 */
public class 完全二叉树的节点个数_222 {

    /**
     * 通用的方法
     */
    public int countNodes0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes0(root.left) + countNodes0(root.right);
    }

    /**
     * 利用完全二叉树的特性
     */
    public int countNodes(TreeNode root) {
        // 利用完全二叉树的特性
        TreeNode leftChild = root, rightChild = root;
        int hl = 0, hr = 0;
        while(leftChild != null) {
            hl++;
            leftChild = leftChild.left;
        }
        while(rightChild != null) {
            hr++;
            rightChild = rightChild.right;
        }

        if(hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
