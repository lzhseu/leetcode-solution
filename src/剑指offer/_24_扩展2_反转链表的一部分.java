package 剑指offer;

import leetcode.数据结构.链表.ListNode;

import static 剑指offer._24_扩展1_反转链表前N个节点.reverseListN;

/**
 * @author lzh
 * @date 2021/1/15 15:33
 */
public class _24_扩展2_反转链表的一部分 {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if(m == 1) {
            return reverseListN(head, n);
        }

        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}
