package leetcode.设计;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lzh
 * @date 2020/11/24 11:04
 */
public class 黑名单中的随机数_710 {

    static class Solution {

        // 思路：把黑名单中的数都交换到数组的后面
        // 每次去随机数的范围就在 0 ~ N - blacklist.length

        private int sz;
        private Map<Integer, Integer> mapping = new HashMap<>();

        public Solution(int N, int[] blacklist) {
            // 最终数组中元素的个数
            sz = N - blacklist.length;

            // 先把黑名单中的数放入 mapping
            for(int b : blacklist) {
                mapping.put(b, -1);
            }

            // 把黑名单中的数交换到后面
            // 需要注意几种情况：
            // 1. 最后的数字不在黑名单中，则交换
            // 2. 最后的数字也在黑名单中，则向前寻找直到一个不在黑名单中的数字，交换
            // 3. 已经在 [sz, N) 之间的数不需要交换
            int last  = N - 1;
            for(int b : blacklist) {

                // 如果 b 已经在区间 [sz, N)
                // 可以直接忽略
                if(b >= sz) {
                    continue;
                }

                // 跳过所有黑名单中的数字
                while(mapping.containsKey(last)) {
                    last--;
                }

                // 将黑名单中的索引映射到合法数字
                mapping.put(b, last);
                last--;
            }

        }

        public int pick() {
            int index = new Random().nextInt(sz);
            if(mapping.containsKey(index)) {
                return mapping.get(index);
            }
            return index;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution(2, new int[] {});
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
        System.out.println(solution.pick());
    }
}
