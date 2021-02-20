package 剑指offer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2021/2/19 20:37
 */
public class _59_1_滑动窗口的最大值 {

    /**
     * 双端队列
     *
     * 14 ms 71.52%    45.9 MB 93.45%
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums == null || nums.length == 0) {
            return new int[] {};
        }

        Deque<Integer> deque = new ArrayDeque<>(k);

        int len = nums.length;
        int[] result = new int[len - k + 1];

        for(int i = 0; i < len; i++) {

            while(!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            // 判断队首的值是否有效
            if(i - deque.peekFirst() + 1 > k) {
                deque.pollFirst();
            }

            if(i + 1 >= k) {
                result[i + 1 - k] = nums[deque.peekFirst()];
            }

        }

        return result;

    }


    @Test
    public void test() {

        int[] nums = {1, 3, 1, 2, 0, 5};

        maxSlidingWindow(nums, 3);
    }
}
