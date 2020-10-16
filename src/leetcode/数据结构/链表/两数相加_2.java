package leetcode.数据结构.链表;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/7/12 10:14
 */
public class 两数相加_2 {

    /**
     * 思路很简单，就是要注意细节  2ms   99.87%
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode res = new ListNode(0);
        ListNode resNext = res;

        // 进位
        int carry = 0;

        ListNode tmp1 = l1, tmp2 = l2;
        while(tmp1 != null || tmp2 != null) {

            ListNode tmpNode;
            int n1 = tmp1 == null ? 0 : tmp1.val;
            int n2 = tmp2 == null ? 0 : tmp2.val;
            int sum = n1 + n2 + carry;

            if(sum > 9) {
                tmpNode = new ListNode(sum%10);
                carry = 1;
            } else {
                tmpNode = new ListNode(sum);
                carry = 0;
            }

            resNext.next = tmpNode;
            resNext = tmpNode;

            if(tmp1 != null) {
                tmp1 = tmp1.next;
            }
            if(tmp2 !=null) {
                tmp2 = tmp2.next;
            }

        }

        if(carry == 1) {
            resNext.next = new ListNode(1);
        }
        return res.next;
    }



    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    @Test
    public void test() {
        int a, b;
        a = b = 100;
        System.out.println(a);
        System.out.println(b);
    }
}
