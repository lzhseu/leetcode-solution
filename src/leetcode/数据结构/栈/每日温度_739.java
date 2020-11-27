package leetcode.数据结构.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/25 9:53
 */
public class 每日温度_739 {
    /**
     * 单调栈的典型应用
     */
    public int[] dailyTemperatures(int[] T) {

        // 典型单调栈
        Deque<Integer> deque = new LinkedList<>();
        int len = T.length;
        int[] res = new int[len];

        for(int i = len - 1; i >= 0; i--) {
            while(!deque.isEmpty() && T[deque.peekLast()] <= T[i]) {
                deque.removeLast();
            }
            res[i] = deque.isEmpty() ? 0 : deque.peekLast() - i;
            deque.addLast(i);
        }
        return res;
    }
}
