package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/1/14 14:46
 */
public class _18_2_删除链表中的重复节点 {

    /**
     * LeetCode 的题目跟 剑指Offer 书中题目不一样
     * LeetCode：有重复节点，只保留一个
     */

    /**
     * 做法一：一个一个删除
     */
    public ListNode deleteDuplicates(ListNode head) {

        ListNode pCurr = head;

        while(pCurr != null && pCurr.next != null) {
            if(pCurr.val == pCurr.next.val) {
                pCurr.next = pCurr.next.next;
            } else {
                pCurr = pCurr.next;
            }
        }

        return head;
    }

    /**
     * 快慢指针：思想可以学习
     */
    public ListNode deleteDuplicates_slow_fast(ListNode head) {

        ListNode fast = head, slow = head;

        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        return head;
    }

    /**
     * 剑指Offer：如果有重复的节点，则所有重复节点都不要，一个不留
     */
    public ListNode deleteDuplication(ListNode head) {

        ListNode pPre = null;
        ListNode pCurr = head;

        while (pCurr != null) {

            if (pCurr.next != null && pCurr.val == pCurr.next.val) {

                ListNode pToBeNext = pCurr.next;
                int value = pCurr.val;

                while (pToBeNext != null && pToBeNext.val == value) {
                    pToBeNext = pToBeNext.next;
                }

                if (pPre == null) {
                    head = pToBeNext;
                } else {
                    pPre.next = pToBeNext;
                }

                pCurr = pToBeNext;

            } else {
                pPre = pCurr;
                pCurr = pCurr.next;
            }
        }

        return head;
    }
}
