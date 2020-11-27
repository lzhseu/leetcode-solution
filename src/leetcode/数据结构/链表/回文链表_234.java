package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/11/20 16:11
 */
public class 回文链表_234 {

    /**
     * 做法1：利用链表的后续遍历
     * 2 ms 58.78%    44.8 MB 5.01%
     */
    ListNode left;

    public boolean isPalindrome1(ListNode head) {
        left = head;
        return traverse(head);
    }

    /**
     * 利用的是后续遍历
     * 这么做的核心逻辑实际上就是把链表节点放入一个栈，
     * 然后再拿出来，这时候元素顺序就是反的，只不过我们利用的是递归函数的堆栈而已。
     */
    private boolean traverse(ListNode right) {
        if(right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        res = res && (left.val == right.val);
        left = left.next;
        return res;
    }

    /**
     * 做法2：快慢指针找中点，翻转后半部分的链表，再分别从左边和右边比较
     * 1 ms 99.95%    40.5 MB 99.54%
     */
    public boolean isPalindrome2(ListNode head) {

        // 快慢指针寻找中点
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast != null) {
            slow = slow.next;
        }

        // 从 slow 开始反转后面的链表
        ListNode right = reverse(slow);
        ListNode left = head;

        // 开始从左边和右边比较
        while(right != null) {
            if(left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {

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
