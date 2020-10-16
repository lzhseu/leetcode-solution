package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/10/3 19:04
 */
public class 合并两个有序链表_21 {

    /**
     * 迭代
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0), p = head;
        ListNode p1 = l1, p2 = l2;
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
        return head.next;
    }

    /**
     * 递归
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        return helper(l1, l2);
    }

    private ListNode helper(ListNode p1, ListNode p2) {

        if(p1 == null) {
            return p2;
        } else if(p2 == null) {
            return p1;
        }

        if(p1.val <= p2.val) {
            p1.next = helper(p1.next, p2);
            return p1;
        } else {
            p2.next = helper(p1, p2.next);
            return p2;
        }
    }
}
