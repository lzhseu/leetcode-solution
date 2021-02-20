package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/1/15 14:26
 */
public class _23_环形链表的入口节点 {

    public ListNode detectCycle(ListNode head) {

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {

                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }
}
