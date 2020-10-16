package leetcode.算法.双指针;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2020/10/3 17:08
 */
public class 删除链表的倒数第N个节点_19 {

    /**
     * 链表的技巧：哑头结点
     */

    /**
     * 官方题解
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p1 = dummy, p2 = dummy;

        for(int i = 0; i < n; i++) {
            p2 = p2.next;
        }

        while(p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        p1.next = p1.next.next;
        return dummy.next;
    }

    /**
     * 长短指针
     * 0ms 100%    37.1 MB 43.47%
     * 自己写的还是太麻烦了
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        ListNode p1 = head, p2 = head;
        int idx1 = 0, idx2 = 0;
        int targetIdx = 0;

        while(true) {

            int i = 0;
            while(p2.next != null && i < n) {
                p2 = p2.next;
                idx2++;
                i++;
            }

            if(p2.next == null) {
                targetIdx = idx2 - n + 1;
                break;
            }

            p1 = p1.next;
            idx1++;
        }

        while(idx1 < targetIdx - 1) {
            p1 = p1.next;
            idx1++;
        }

        if(targetIdx == 0) {
            head = p1.next;
            p1.next = null;
        } else {
            ListNode delete = p1.next;
            p1.next = delete.next;
            delete.next = null;
        }

        return head;
    }
}
