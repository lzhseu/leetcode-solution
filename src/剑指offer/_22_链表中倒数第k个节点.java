package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/1/15 10:45
 */
public class _22_链表中倒数第k个节点 {

    /**
     * 做法一：两次遍历
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {

        // 解法1：顺序遍历

        ListNode p = head;

        int count = 0;

        while(p != null) {
            count++;
            p = p.next;
        }

        if(k > count) {
            throw new RuntimeException("invalid input");
        }

        int nums = count - k;
        p = head;
        for(int i = 0; i < nums; i++) {
            p = p.next;
        }

        return p;
    }

    /**
     * 做法二：一次遍历。双指针
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        ListNode slow = head, fast = head;

        int i = 1;
        while(i < k && fast != null) {
            fast = fast.next;
            i++;
        }

        if(fast == null) {
            throw new RuntimeException("invalid input");
        }

        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

}
