package 剑指offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2021/1/16 11:01
 */
public class _30_包含min函数的栈 {

    static class MinStack {

        private Deque<Integer> dataStack;
        private Deque<Integer> minStack;

        /** initialize your data structure here. */
        public MinStack() {
            dataStack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int x) {
            if(minStack.isEmpty() || minStack.peek() > x) {
                minStack.push(x);
            } else {
                minStack.push(minStack.peek());
            }
            dataStack.push(x);
        }

        public void pop() {
            if(!dataStack.isEmpty()) {
                dataStack.pop();
                minStack.pop();
            }
        }

        public int top() {
            if(dataStack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return dataStack.peek();
        }

        public int min() {
            if(dataStack.isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return minStack.peek();
        }
    }
}
