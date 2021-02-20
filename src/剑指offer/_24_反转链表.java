package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/1/15 14:53
 */
public class _24_反转链表 {

    /**
     * 解法一：循环遍历
     */
    public ListNode reverseList_cycle(ListNode head) {

        ListNode pPre = null;
        ListNode pCurr = head;
        ListNode pNext = head;

        while(pCurr != null) {
            pNext = pCurr.next;
            pCurr.next = pPre;
            pPre = pCurr;
            pCurr = pNext;
        }

        return pPre;
    }

    /**
     * 解法二：递归。更加简洁巧妙。推荐。
     */
    public ListNode reverseList(ListNode head) {

        if(head == null || head.next == null) {
            return head;
        }

        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

}
