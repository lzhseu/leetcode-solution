package leetcode.数据结构.链表;

/**
 * @author lzh
 * @date 2020/11/20 15:41
 */
public class 对链表进行插入排序_147 {

    /**
     * 流程：【不就是链表的基础操作吗：删除、遍历、插入】
     * 1. 拿出节点
     * 	- curr.val < prev.val：从链表中删除该节点，并执行2
     * 	- 否则，取下一节点继续
     * 2. 把拿出的节点从头开始遍历，找到合适的位置插入
     *
     * 小技巧：
     * 1. 加入哑节点
     *
     * 3 ms 98.81%    38.3 MB 67.22%
     */
    public ListNode insertionSortList(ListNode head) {

        if(head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode curr = head.next, prev = head;

        while(curr != null) {

            if(curr.val < prev.val) {
                // 删除该节点
                ListNode selectNode = curr;
                prev.next = curr.next;
                curr.next = null;

                // 从头寻找合适的位置插入
                ListNode tmp = dummy;
                while(tmp.next.val < selectNode.val) {
                    tmp = tmp.next;
                }
                selectNode.next = tmp.next;
                tmp.next = selectNode;

                // 从 prev 的下一位置继续
                curr = prev.next;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        return dummy.next;

    }
}
