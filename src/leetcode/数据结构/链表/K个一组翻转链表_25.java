package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/11/20 11:12
 */
public class K个一组翻转链表_25 {

    /**
     * 与翻转链表的前 k 项相结合
     * 0 ms 100.00%
     */
    private ListNode successor = null;

    public ListNode reverseKGroup(ListNode head, int k) {

        // base case
        if(head == null) {
            return null;
        }
        // 要先知道够不够k
        ListNode tmp = head;
        for(int i = 0; i < k; i++) {
            if(tmp == null) {
                return head;
            }
            tmp = tmp.next;
        }

        // 翻转前k个节点
        ListNode newHead = reverseK(head, k);

        head.next = reverseKGroup(successor, k);

        return newHead;
    }

    /**
     * 递归版 39 MB 36.89%
     */
    private ListNode reverseK(ListNode head, int k) {

        if(k == 1) {
            successor = head.next;
            return head;
        }

        ListNode newHead = reverseK(head.next, k - 1);
        head.next.next = head;
        head.next = successor;

        return newHead;
    }

    /**
     * 迭代版 38.8M 65.64%
     */
    private ListNode reverseK2(ListNode head, int k) {

        ListNode curr = head, prev = null, next = head;

        while(k > 0 && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            k--;
        }

        // head.next = curr;
        successor = curr;
        return prev;
    }


    /**
     * 与翻转 [a, b) 之间的部分相结合
     * 0 ms 100.00%    38.5 MB 92.24%
     */
    public ListNode reverseKGroup2(ListNode head, int k) {

        // base case
        if(head == null) {
            return null;
        }
        // 要先知道够不够k
        ListNode a = head, b = head;
        for(int i = 0; i < k; i++) {
            if(b == null) {
                return head;
            }
            b = b.next;
        }

        // 翻转[a, b)之间的节点
        ListNode newHead = reverseAtoB(a, b);

        head.next = reverseKGroup2(b, k);

        return newHead;
    }


    private ListNode reverseAtoB(ListNode a, ListNode b) {

        ListNode curr = a, prev = null, next = a;

        while(curr != b) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        //a.next = curr;
        return prev;
    }
}
