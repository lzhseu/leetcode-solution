package 剑指offer;

import leetcode.数据结构.链表.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2021/1/13 11:22
 */
public class _06_从尾到头打印链表 {

    /**
     * 循环
     * 0 ms 100.00%    39.2 MB 40.53%
     */
    public int[] reversePrint_for(ListNode head) {

        int count = 0;
        ListNode p = head;
        while(p != null) {
            count++;
            p = p.next;
        }

        int[] res = new int[count];

        p = head;
        while(p != null) {
            res[--count] = p.val;
            p = p.next;
        }

        return res;
    }


    /**
     * 递归。能用递归就能用栈
     * 0 ms 100.00%    39.9 MB 8.59%
     */
    public int[] reversePrint_recursive(ListNode head) {

        int count = 0;
        ListNode p = head;
        while(p != null) {
            count++;
            p = p.next;
        }

        int[] res = new int[count];

        revertList(head, res, count - 1);

        return res;
    }

    private void revertList(ListNode head, int[] res, int i) {

        if(head == null) {
            return;
        }

        revertList(head.next, res, i - 1);
        res[i] = head.val;
    }
}
