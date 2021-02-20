package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/2/18 15:13
 */
public class _52_两个链表的第一个公共节点 {

    /**
     * 写法一：先看是否有公共尾节点，有则比较两条链表相差多少步，让长的链表先走 delta 步，然后两条链表一起走，直到相遇
     * 1 ms 100.00%    1.3 MB 50.12%
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) {
            return null;
        }

        int countA = 1, countB = 1;

        ListNode pA = headA, pB = headB;

        while(pA.next != null) {
            countA++;
            pA = pA.next;
        }

        while(pB.next != null) {
            countB++;
            pB = pB.next;
        }

        if(pA != pB) {
            return null;
        }

        // 到这里说明有公共节点

        pA = headA;
        pB = headB;
        int delta = Math.abs(countA - countB);

        if(countA < countB) {
            while(delta > 0) {
                pB = pB.next;
                delta--;
            }
        } else if(countB < countA) {
            while(delta > 0) {
                pA = pA.next;
                delta--;
            }
        }

        while(pA != pB) {
            pA = pA.next;
            pB = pB.next;
        }

        return pA;

    }


    /**
     * 写法二：让两条链遍历完自身后再遍历一遍对方的，直到相遇或都为 null
     * 1 ms 100.00%    41.4 MB 30.06%
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) {
            return null;
        }

        ListNode pA = headA, pB = headB;

        while(true) {

            if(pA == null) {
                pA = headB;
            }

            if(pB == null) {
                pB = headA;
            }

            if(pA == pB) {
                return pA;
            }

            pA = pA.next;
            pB = pB.next;

            if(pA == null && pB == null) {
                return null;
            }
        }

    }
}
