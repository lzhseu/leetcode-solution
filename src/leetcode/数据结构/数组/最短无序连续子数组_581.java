package leetcode.数据结构.数组;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author lzh
 * @date 2021/1/8 15:39
 */
public class 最短无序连续子数组_581 {

    /**
     * 方法一：排序
     * 8 ms 40.57%    39.9 MB 53.60%
     */
    public int findUnsortedSubarray1(int[] nums) {

        int len = nums.length;

        int[] orderNums = new int[len];

        System.arraycopy(nums, 0, orderNums, 0, len);

        Arrays.sort(orderNums);

        int begin = -1, end = -1;
        for(int i = 0; i < len; i++) {
            if(nums[i] != orderNums[i]) {
                if(begin == -1) {
                    begin = i;
                }
                end = i;
            }
        }

        return begin == -1 ? 0 : end - begin + 1;
    }

    /**
     * 方法二：单调栈
     * 9 ms 28.48%    39.4 MB 93.88%
     */
    public int findUnsortedSubarray2(int[] nums) {

        int len = nums.length;

        Deque<Integer> deque = new ArrayDeque<>();

        int begin = Integer.MAX_VALUE, end = Integer.MIN_VALUE;

        for(int i = 0; i < len; i++) {

            while(!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {

                int top = deque.removeLast();
                begin = Math.min(top, begin);
            }

            deque.addLast(i);
        }

        deque.clear();

        for(int i = len - 1; i >= 0; i--) {

            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {

                int top = deque.removeLast();
                end = Math.max(top, end);
            }

            deque.addLast(i);
        }

        return begin == Integer.MAX_VALUE ? 0 : end - begin + 1;
    }


    /**
     * 方法三：跟栈的思路差不多，但不用栈
     * 1 ms 100.00%    40 MB 40.48%
     */
    public int findUnsortedSubarray3(int[] nums) {

        int len = nums.length;

        int begin = len, end = 0;

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++) {
            if(nums[i] < max) {
                end = i;
            } else {
                max = nums[i];
            }

        }

        int min = Integer.MAX_VALUE;
        for(int i = len - 1; i >= 0; i--) {
            if(nums[i] > min) {
                begin = i;
            } else {
                min = nums[i];
            }
        }

        return end - begin >= 0 ? end - begin + 1 : 0;
    }
}
