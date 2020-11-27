package leetcode.数据结构.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/25 9:34
 */
public class 下一个更大元素II_503 {

    /**
     * 单调栈的典型应用
     */
    public int[] nextGreaterElements(int[] nums) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return nums;
        }

        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[len];

        for(int i = 2 * len - 1; i >= 0; i--) {
            int index = i % len;
            while(!deque.isEmpty() && deque.peekLast() <= nums[index]) {
                deque.removeLast();
            }
            res[index] = deque.isEmpty() ? -1 : deque.peekLast();
            deque.addLast(nums[index]);
        }

        return res;
    }
}
