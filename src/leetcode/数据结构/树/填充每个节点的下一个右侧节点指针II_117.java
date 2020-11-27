package leetcode.数据结构.树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lzh
 * @date 2020/11/19 10:21
 */
public class 填充每个节点的下一个右侧节点指针II_117 {

    /**
     * 层序遍历：利用队列【常规就是利用队列】
     * 使用了额外空间
     * 2 ms 55.30%    38 MB 97.16%
     */
    public Node connect1(Node root) {
        if(root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            Node prev = null;
            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                if(prev != null) {
                    prev.next = node;
                }
                prev = node;
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }

    /**
     * 层序遍历：不利用队列
     * 0 ms 100.00%    38.2 MB 90.82%
     */
    public Node connect2(Node root) {

        // cur 我们可以把它看做是每一层的链表
        Node curr = root;

        while(curr != null) {

            // 遍历当前层的时候，为了方便操作在下一
            // 层前面添加一个哑结点（注意这里是访问
            // 当前层的节点，然后把下一层的节点串起来）
            Node head = new Node();
            // p 表示访下一层节点的前一个节点
            Node p = head;

            while(curr != null) {

                // 如果当前节点的左子节点不为空，就让 p 节点
                // 的 next 指向他，也就是把它串起来
                if(curr.left != null) {
                    p.next = curr.left;
                    p = p.next;
                }
                if(curr.right != null) {
                    p.next = curr.right;
                    p = p.next;
                }

                // 继续访问这一行的下一个节点
                curr = curr.next;
            }

            // 把下一层串联成一个链表之后，让他赋值给 cur，
            // 后续继续循环，直到 cur 为空为止
            curr = head.next;
        }

        return root;
    }
}
