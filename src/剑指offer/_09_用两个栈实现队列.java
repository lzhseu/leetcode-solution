package 剑指offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2021/1/13 14:59
 */
public class _09_用两个栈实现队列 {

    class CQueue {

        private Deque<Integer> stack1;
        private Deque<Integer> stack2;

        public CQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {

            if(!stack2.isEmpty()) {
                return stack2.pop();
            }

            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }

            return stack2.isEmpty() ? -1 : stack2.pop();
        }
    }
}
