package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/11/21 11:04
 */
public class 环形链表II_142 {

    public ListNode detectCycle(ListNode head) {

        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // 相遇，说明有环形
            if(slow == fast) {
                ListNode meetingNode = fast;
                ListNode index = head;
                while(meetingNode != index) {
                    meetingNode = meetingNode.next;
                    index = index.next;
                }
                return index;
            }
        }

        return null;
    }
}
