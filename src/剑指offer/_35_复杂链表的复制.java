package 剑指offer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2021/1/19 18:54
 */
public class _35_复杂链表的复制 {

    /**
     * 方法一：哈希表，空间换时间
     * 0 ms  100.00%    37.9 MB  73.90%
     */
    public Node copyRandomList_hashmap(Node head) {

        if(head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();

        Node old = head;
        Node newHead = new Node(head.val);
        Node p = newHead;

        map.put(head, newHead);


        // 第一次复制 next
        while(old.next != null) {

            p.next = new Node(old.next.val);
            p = p.next;
            old = old.next;

            map.put(old, p);
        }

        // 第二次连接 random
        old = head;
        p = newHead;

        while(old != null) {
            if(old.random != null) {
                p.random = map.get(old.random);
            }
            old = old.next;
            p = p.next;
        }

        return newHead;
    }


    /**
     * 方法二：
     * 1. 复制链表中的每个节点，并且把复制的节点连在原节点后面，如 A -> A' -> B -> B' -> ...
     * 2. 复制 random，A'.random = A.random.next;
     * 3. 拆分成两个链表
     **/
    public Node copyRandomList(Node head) {

        // 方法二：不需要 HashMap，分三步
        // 1. 复制链表中的每个节点，并且把复制的节点连在原节点后面，如 A -> A' -> B -> B' -> ...
        // 2. 复制 random，A'.random = A.random.next;
        // 3. 拆分成两个链表

        if(head == null) {
            return null;
        }

        // 1. 复制每个节点
        copyAllNodes(head);

        // 2. 复制 random
        copyRandomPointer(head);

        // 3. 拆分成两个链表
        return splitList(head);

    }


    /**
     * 以下三个方法使用递归实现，递归要使用更多的栈空间
     */
    private void copyAllNodes(Node head) {

        if(head == null) {
            return;
        }

        Node newNode = new Node(head.val);
        Node oldNext = head.next;
        head.next = newNode;
        newNode.next = oldNext;

        copyAllNodes(oldNext);
    }

    private void copyRandomPointer(Node head) {

        if(head == null) {
            return;
        }

        if(head.random == null) {
            head.next.random = null;
        } else {
            head.next.random = head.random.next;
        }

        copyRandomPointer(head.next.next);
    }

    private Node splitList(Node head) {

        if(head == null) {
            return null;
        }

        Node newHead = head.next;
        head.next = newHead.next;
        newHead.next = splitList(head.next);
        return newHead;
    }

    /**
     * 以下三个方法使用循环实现
     */
    private Node copyAllNodes_loop(Node head) {

        Node p = head;
        while(p != null) {

            Node node = new Node(p.val);
            Node pNext = p.next;
            p.next = node;
            node.next = pNext;
            p = pNext;
        }

        return head;
    }

    private Node copyRandomPointer_loop(Node head) {

        Node p = head;

        while(p != null) {
            if(p.random == null) {
                p.next.random = null;
            } else {
                p.next.random = p.random.next;
            }

            p = p.next.next;
        }

        return head;
    }

    private Node splitList_loop(Node head) {

        Node newHead = head.next;
        Node newp = newHead;
        Node oldp = head;

        while(oldp != null) {
            oldp.next = newp.next;
            oldp = oldp.next;
            if(oldp == null) {
                newp.next = null;
            } else {
                newp.next = oldp.next;
            }
            newp = newp.next;
        }

        return newHead;
    }



    /**
     * 节点
     */
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


}
