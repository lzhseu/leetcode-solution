package 剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2021/1/13 15:19
 */
public class _10_1_斐波那契数列 {

    /**
     * 解法1：记忆化递归
     */
    private Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int n) {

        if(n == 0) {
            return 0;
        }

        if(n == 1) {
            return 1;
        }

        if(cache.containsKey(n)) {
            return cache.get(n);
        }

        int res = (fib(n - 1) + fib(n - 2)) % 1000000007;
        cache.put(n, res);

        return res;
    }


    /**
     * 解法2：动态规划
     */
    public int fib_dp(int n) {

        if(n <= 1) {
            return n;
        }

        int last = 1, llast = 0;

        for(int i = 2; i <= n; i++) {
            int tmp = (llast + last) % 1000000007;
            llast = last;
            last = tmp;
        }

        return last;
    }
}
