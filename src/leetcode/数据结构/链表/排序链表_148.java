package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/11/21 9:32
 */
public class 排序链表_148 {

    /**
     * 递归版归并排序【需要 O(logN) 的空间】
     * 7 ms 40.32%    46.9 MB 8.92%
     */
    // 递归解答
    public ListNode sortList(ListNode head) {

        // base case
        if(head == null || head.next == null) {
            return head;
        }

        // 找中点
        ListNode midNode = findMidNode(head);
        // 右边链表的头结点
        ListNode secHead = midNode.next;
        // 断开链表
        midNode.next = null;
        // 递归
        ListNode left = sortList(head);
        ListNode right = sortList(secHead);

        // 合并
        return mergeTwoList(left, right);
    }

    private ListNode findMidNode(ListNode head) {
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode mergeTwoList(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(), curr = dummy;
        while(left != null && right != null) {
            if(left.val <= right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }

        curr.next = left == null ? right : left;
        return dummy.next;
    }

    /**
     * 迭代版归并排序【这种编码太难了】
     * 11 ms 20.78%    47 MB 5.62%
     */
    public ListNode sortList2(ListNode head) {

        if(head == null) {
            return head;
        }

        // 1.统计链表长度
        int length = 0;
        for(ListNode p = head; p != null; p = p.next) {
            length++;
        }

        // 2.初始化
        ListNode dummy = new ListNode();
        dummy.next = head;

        // 3.每次将链表拆分成若干个长度为subLen的子链表 , 并按照每两个子链表一组进行合并
        for(int subLen = 1; subLen < length; subLen <<= 1) {

            // curr用于记录拆分链表的位置
            ListNode prev = dummy;
            ListNode curr = dummy.next;

            // 开始按 subLen 拆分链表
            while(curr != null) {

                // 3.1 找出第一个链表
                ListNode head_1 = curr;
                // 找第一个链表的尾边界
                for(int i = 1; i < subLen && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                // 3.2 找出第二个链表
                ListNode head_2 = curr.next;
                // 断开与前面链表的连接
                curr.next = null;
                // 从第二个链表头开始找尾边界
                curr = head_2;
                for(int i = 1; i < subLen && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }

                // 3.3 用 next 记录之后的起始位置，并断开第二个链表与之后的连接
                ListNode next = null;
                if(curr != null) {
                    next = curr.next;
                    curr.next = null;
                }

                // 3.4 合并两个链表
                ListNode mergeNode = mergeTwoList(head_1, head_2);
                // prev.next 指向排好序链表的头
                prev.next = mergeNode;
                // 把 prev 已到尾部，下次又可以连接新合并的链表
                while(prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }

        }

        return dummy.next;
    }

}
