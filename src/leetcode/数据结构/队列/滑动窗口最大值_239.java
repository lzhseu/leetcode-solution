package leetcode.数据结构.队列;

import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/26 10:01
 */
public class 滑动窗口最大值_239 {

    /**
     * 使用单调队列
     */
    public int[] maxSlidingWindow(int[] nums, int k) {

        MonotonicQueue queue = new MonotonicQueue();

        int len = nums.length;
        int[] res = new int[len - k + 1];

        for(int i = 0; i < k - 1; i++) {
            queue.push(nums[i]);
        }

        for(int i = k - 1; i < len; i++) {
            queue.push(nums[i]);
            res[i - k + 1] = queue.max();
            queue.pop(nums[i - k + 1]);
        }

        return res;
    }

    /**
     * 单调队列
     */
    static class MonotonicQueue {

        private LinkedList<Integer> queue = new LinkedList<>();

        public void push(int n) {
            while(!queue.isEmpty() && queue.getLast() < n) {
                queue.removeLast();
            }
            queue.addLast(n);
        }

        public void pop(int n) {
            if(!queue.isEmpty() && queue.getFirst() == n) {
                queue.removeFirst();
            }
        }

        public int max() {
            return queue.getFirst();
        }
    }


}
