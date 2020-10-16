package leetcode.算法.分治;

import leetcode.数据结构.链表.ListNode;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author lzh
 * @date 2020/10/4 19:37
 */
public class 合并K个升序链表_23 {

    /**
     * 分治
     * 1ms 100%     40.7M 54.88%
     */
    public ListNode mergeKLists(ListNode[] lists) {

        int len;
        if(lists == null || (len = lists.length) == 0) {
            return null;
        } else if(len == 1) {
            return lists[0];
        }

        return forkJoin(lists, 0, len - 1);

    }

    private ListNode forkJoin(ListNode[] lists, int left, int right) {

        if(right - left + 1 == 2) {
            return mergeTwoLists(lists[left], lists[right]);
        } else if(right == left) {
            return mergeTwoLists(lists[left], null);
        }

        int mid = left + ((right - left) >> 1);
        ListNode leftList = forkJoin(lists, left, mid);
        ListNode rightList = forkJoin(lists, mid + 1, right);
        return mergeTwoLists(leftList, rightList);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), h = dummy;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                h.next = l1;
                l1 = l1.next;
            } else {
                h.next = l2;
                l2 = l2.next;
            }
            h = h.next;
        }

        h.next = l1 == null ? l2 : l1;

        return dummy.next;
    }


    /**
     * 最小堆
     * 6ms 58.49%    40.7M
     */
    public ListNode mergeKLists2(ListNode[] lists) {

        int len;
        if(lists == null || (len = lists.length) == 0) {
            return null;
        } else if(len == 1) {
            return lists[0];
        }

        Queue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });

        for(ListNode node : lists) {
            if(node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(), p = dummy;
        while(!pq.isEmpty()) {
            ListNode node = pq.poll();
            p.next = node;
            p = p.next;
            if(node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }


}
