package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/1/15 16:57
 */
public class _25_合并两个排序的链表 {

    /**
     * 方法一：循环
     */
    public ListNode mergeTwoLists_cycle(ListNode l1, ListNode l2) {

        ListNode p1 = l1, p2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;

        while(p1 != null && p2 != null) {

            if(p1.val <= p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }

            p = p.next;
        }

        p.next = p1 == null ? p2 : p1;

        return dummy.next;
    }


    /**
     * 方法二：递归
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if(l1 == null) {
            return l2;
        } else if(l2 == null) {
            return l1;
        }

        if(l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
