package leetcode.算法.贪心;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/11/18 19:22
 */
public class 加油站_134 {

    /**
     * 方法一：暴力解法。即模拟整个过程
     * 87ms 16.46    38.7M 86.18%
     */
    public int canCompleteCircuit1(int[] gas, int[] cost) {

        // 暴力解法，是一种模拟算法
        int len = gas.length;
        for(int i = 0; i < len; i++) {
            if(gas[i] > cost[i]) {
                // 这里才开始尝试
                if(helper(gas, cost, i, len)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean helper(int[] gas, int[] cost, int start, int len) {
        int end = start + len;
        int oil = 0;
        for(int i = start; i < end; i++) {
            int idx = i % len;
            // i -> i+1
            oil = oil + gas[idx] - cost[idx];
            if(oil < 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 方法二：贪心算法
     */

    @Test
    public void test() {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 4};
        System.out.println(canCompleteCircuit1(gas, cost));;
    }
}
