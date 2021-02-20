package 剑指offer;

import leetcode.数据结构.链表.ListNode;

/**
 * @author lzh
 * @date 2021/1/14 14:24
 */
public class _18_删除链表的节点 {

    /**
     * 技巧：引入哑节点
     */
    public ListNode deleteNode(ListNode head, int val) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pCurr = dummy;

        while(pCurr.next != null) {
            if(pCurr.next.val == val) {
                pCurr.next = pCurr.next.next;
                break;
            }
            pCurr = pCurr.next;
        }

        return dummy.next;
    }

    /**
     * 剑指Offer 原题
     * 要全面考虑各种情况
     */
    public ListNode deleteNode(ListNode head, ListNode deletedNode) {

        if (head == null || deletedNode == null) {
            return null;
        }

        if (deletedNode.next != null) {

            // 如果待删除节点不是尾节点，则采用复制后删除的做法，O(1)

            deletedNode.val = deletedNode.next.val;
            deletedNode.next = deletedNode.next.next;

            return head;

        } else if (head == deletedNode) {

            // 如果链表中只有一个节点，删除头结点
            return null;

        } else {

            // 要删除的是尾节点，那么只能退化成顺序遍历到尾节点的前一个节点

            ListNode p = head;
            while (p.next != deletedNode) {
                p = p.next;
            }

            p.next = null;
            return head;
        }
    }
}
