package 剑指offer;

import leetcode.数据结构.树.Node;

/**
 * @author lzh
 * @date 2021/2/15 14:37
 */
public class _36_二叉树与双向链表 {

    private Node pre = null;
    private Node head = null;

    public Node treeToDoublyList(Node root) {

        if(root == null) {
            return null;
        }

        dfs(root);

        head.left = pre;
        pre.right = head;

        return head;
    }


    private void dfs(Node root) {

        // base case
        if(root == null) {
            return;
        }

        dfs(root.left);

        if(pre == null) {
            head = root;
        } else {
            pre.right = root;
            root.left = pre;
        }
        pre = root;

        dfs(root.right);
    }
}
