package leetcode.数据结构.树;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/13 11:09
 */
public class 二叉树展开为链表_114 {

    /**
     * 通过递归框架，建议学习
     * 0ms
     */
    public void flatten(TreeNode root) {

        if(root == null) {
            return;
        }

        // 1.0 分别拉平左右子树
        flatten(root.left);
        flatten(root.right);

        // 1.1 左右子树已经被拉平为链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2 把左子树作为右子树
        root.left = null;
        root.right = left;

        // 3 把右子树接到原左子树的下面
        TreeNode p = root;
        while(p.right != null) {
            p = p.right;
        }
        p.right = right;

    }

    /**
     * 解法1：
     * 将原来的右子树接到左子树的最右边节点
     * 将左子树插入到右子树的地方
     * 将原来左子树置为null
     * 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     * 0ms 100%    38M 86.26%
     */
    public void flatten1(TreeNode root) {

        while(root != null) {

            if(root.left != null) {

                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while(pre.right != null) {
                    pre = pre.right;
                }

                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;

            }
            root = root.right;
        }
    }


    /**
     * 解法2：递归
     * 0ms    38.2M 74.87%
     */
    private TreeNode prev = null;

    public void flatten2(TreeNode root) {

        helper(root);
    }

    // 不就是一个【右->左->中】的后序遍历
    private void helper(TreeNode root) {

        // 终止条件
        if(root == null) {
            return;
        }

        helper(root.right);
        helper(root.left);

        root.right = prev;
        root.left = null;
        prev = root;

    }


    /**
     * 特殊的先序遍历，提前将右孩子保存到栈中，我们利用这种遍历方式就可以防止右孩子的丢失
     * 1ms 46.28%    37.8M 97.06%
     */
    public void flatten3(TreeNode root) {

        if(root == null) {
            return;
        }

        TreeNode prev = null;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while(!stack.isEmpty()) {

            TreeNode tmp = stack.pop();

            if(prev != null) {
                prev.right = tmp;
                prev.left = null;
            }

            if(tmp.right != null) {
                stack.push(tmp.right);
            }

            if(tmp.left != null) {
                stack.push(tmp.left);
            }

            prev = tmp;
        }
    }


    /**
     * 题目要求使用原地展开的算法
     * 这里是不符合要求的算法
     */
    private TreeNode ph;

    public void flatten0(TreeNode root) {
        TreeNode head = new TreeNode();
        ph = head;
        helper2(root);
        TreeNode node = head.right;
        while(node != null) {
            System.out.println(node.val);
            node = node.right;
        }
    }

    private void helper2(TreeNode root) {

        // 终止条件
        if(root == null) {
            return;
        }

        ph.right = new TreeNode(root.val);
        ph = ph.right;

        helper2(root.left);
        helper2(root.right);

    }
}
