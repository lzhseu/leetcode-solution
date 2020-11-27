package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/11/19 16:03
 */
public class 反转链表II_92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {

        if(m == 1) {
            return reversN(head, n);
        }

        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    ListNode successor = null;
    public ListNode reversN(ListNode head, int n) {

        if(n == 1) {
            successor = head.next;
            return head;
        }

        ListNode last = reversN(head.next, n - 1);

        head.next.next = head;
        head.next = successor;

        return last;
    }
}
