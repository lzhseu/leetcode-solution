package leetcode.数据结构.树;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lzh
 * @date 2020/11/12 10:28
 */
public class 对称二叉树_101 {

    /**
     * 递归
     * 0ms 100%    36.4M 89.8%
     */
    public boolean isSymmetric(TreeNode root) {

        if(root == null) {
            return true;
        }
        return isMirror(root.left, root.right);

    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {

        if(node1 == null && node2 == null) {
            return true;
        }
        if(node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }


    /**
     * 迭代
     * 1ms
     */
    public boolean isSymmetric2(TreeNode root) {

        if(root == null) {
            return true;
        }

        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while(!queue.isEmpty()) {

            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if(node1 == null && node2 == null) {
                continue;
            }
            if(node1 == null || node2 == null) {
                return false;
            }
            if(node1.val != node2.val) {
                return false;
            }

            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);

        }

        return true;
    }

    @Test
    public void test() {
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.add(null);
        Integer i1 = queue.poll();
        System.out.println(i1);
        Integer i2 = queue.poll();
        System.out.println(i2);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(1);
        deque.offerLast(null);
        Integer i3 = deque.pollFirst();
        System.out.println(i3);
        Integer i4 = deque.pollFirst();
        System.out.println(i4);

    }
}
