package 剑指offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2021/1/16 11:26
 */
public class _31_栈的压入弹出序列 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Deque<Integer> deque = new ArrayDeque<>();

        int p = 0;

        for(int i = 0; i < pushed.length; i++) {

            deque.push(pushed[i]);

            while(!deque.isEmpty() && deque.peek() == popped[p]) {
                deque.pop();
                p++;
            }
        }

        return deque.isEmpty();

    }
}
