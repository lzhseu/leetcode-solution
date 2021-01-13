package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/12/21 18:37
 */
public class 相交链表_160 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA, p2 = headB;

        while(true) {

            if(p1 == null) {
                p1 = headB;
            }
            if(p2 == null) {
                p2 = headA;
            }

            if(p1 == p2) {
                return p1;
            }

            p1 = p1.next;
            p2 = p2.next;

            if(p1 == null && p2 == null) {
                return null;
            }
        }

    }

    /**
     * 简写版
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

}
