package leetcode.算法.双指针;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2020/11/24 11:20
 */
public class 删除排序链表中的重复元素_83 {

    /**
     * 快慢指针
     * 交换值还是稍微慢了一点
     * 0 ms 100%    37.8 MB 86.44%
     */
    public ListNode deleteDuplicates(ListNode head) {

        if(head == null) {
            return null;
        }

        ListNode slow = head, fast = head;

        while(fast != null) {
            if(slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        slow.next = null;
        return head;
    }
}
