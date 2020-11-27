package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/11/19 15:36
 */
public class 反转链表_206 {

    /**
     * 递归法
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

    /**
     * 迭代法
     */
    public ListNode reverseList2(ListNode head) {

        // 使用迭代法
        ListNode curr = head, prev = null, next = head;

        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}
