package 剑指offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2021/2/19 23:19
 */
public class _59_2_队列的最大值 {

    static class MaxQueue {

        private Deque<Integer> dataDeque;
        private Deque<Integer> maxDeque;

        public MaxQueue() {
            dataDeque = new ArrayDeque<>();
            maxDeque = new ArrayDeque<>();
        }

        public int max_value() {
            return maxDeque.isEmpty() ? -1 : maxDeque.peekFirst();
        }

        public void push_back(int value) {

            dataDeque.offerLast(value);

            while(!maxDeque.isEmpty() && maxDeque.peekLast() < value) {
                maxDeque.pollLast();
            }

            maxDeque.offerLast(value);
        }

        public int pop_front() {

            if(dataDeque.isEmpty()) {
                return -1;
            }

            int value = dataDeque.pollFirst();
            if(value == maxDeque.peekFirst()) {
                maxDeque.pollFirst();
            }

            return value;
        }
    }
}
