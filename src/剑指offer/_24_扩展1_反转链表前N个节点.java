package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/1/15 15:20
 */
public class _24_扩展1_反转链表前N个节点 {

    private ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {

        if (n == 1) {
            successor = head.next;
            return head;
        } else if (head == null || head.next == null) {
            throw new RuntimeException("invalid input");
        }

        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;

        return last;
    }

    public static ListNode reverseListN(ListNode head, int n) {
        return new _24_扩展1_反转链表前N个节点().reverseN(head, n);
    }
}
