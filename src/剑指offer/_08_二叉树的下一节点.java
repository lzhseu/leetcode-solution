package 剑指offer;



/**
 * @author lzh
 * @date 2021/1/13 14:28
 */
public class _08_二叉树的下一节点 {

    /**
     * 此题 LeetCode 中没有
     */

    TreeNode getNext(TreeNode node) {

        if(node == null) {
            return null;
        }

        TreeNode pNext = null;

        //1. 如果此节点有右子节点，则沿着右子节点的左指针一直寻找
        if (node.right != null) {
            TreeNode p = node.right;
            while (p.left != null) {
                p = p.left;
            }

            pNext = p;

        } else if (node.parent != null) {

            //2. 如果没有右子节点
            //2.1 如果此节点是其父节点的左子节点，则父节点就是下一节点
            //2.2 如果此节点是其父节点的右子节点，则需要往上一直寻找，直到找到 2.1 的情况

            TreeNode pCurr = node;
            TreeNode pParent = pCurr.parent;


            while (pParent != null && pCurr == pParent.right) {
                pCurr = pParent;
                pParent = pCurr.parent;
            }

            pNext = pParent;
        }

        return pNext;
    }

    static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

        public TreeNode(int x) { val = x; }

        public TreeNode() {

        }
    }
}
