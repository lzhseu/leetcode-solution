package leetcode.算法.哈希;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/27 9:36
 */
public class 四数相加II_454 {

    /**
     * 没想到用 两两分组 以降低时间复杂度
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;

        for(int a : A) {
            for(int b : B) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for(int c : C) {
            for(int d : D) {
                int oppo = -(c + d);
                if(map.containsKey(oppo)) {
                    res += map.get(oppo);
                }
            }
        }

        return res;
    }
}
