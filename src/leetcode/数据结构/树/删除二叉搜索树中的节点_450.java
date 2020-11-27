package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/18 15:48
 */
public class 删除二叉搜索树中的节点_450 {

    public TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) {
            return null;
        }

        // 首先找到要删除的位置
        if(root.val == key) {

            // 如果该节点没有子树或者只有一棵子树
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }

            // 如果两棵子树都存在
            // 1. 找到右子树中的最小节点
            // 2. 与该节点交换
            // 2. 递归删除该最小节点
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);

        } else if(root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }

        return root;
    }

    private TreeNode getMin(TreeNode node) {
        // 往左边找就可以了
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
}
