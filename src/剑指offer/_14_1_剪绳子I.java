package 剑指offer;


/**
 * @author lzh
 * @date 2021/1/13 20:41
 */
public class _14_1_剪绳子I {

    /**
     * 解法一：记忆化递归
     * 0 ms 100.00%    35.4 MB 19.84%
     */
    public int cuttingRope(int n) {

        if(n == 2) {
            return 1;
        } else if(n == 3) {
            return 2;
        }

        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        return dp(n, cache);
    }

    private int dp(int n, int[] cache) {

        // base case
        if(cache[n] > 0) {
            return cache[n];
        }

        int maxMulti = n;

        for(int i = 1; i <= n / 2; i++) {
            maxMulti = Math.max(maxMulti, dp(i, cache) * dp(n - i, cache));
        }

        cache[n] = maxMulti;

        return maxMulti;

    }


    /**
     * 解法二：动态规划
     * 0 ms 100.00%    35.5 MB 12.43%
     */
    public int cuttingRope_dp(int n) {

        if(n == 2) {
            return 1;
        } else if(n == 3) {
            return 2;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {

            int maxMulti = i;

            for(int j = 1; j <= i/2; j++) {
                maxMulti = Math.max(maxMulti, dp[j] * dp[i - j]);
            }

            dp[i] = maxMulti;
        }

        return dp[n];

    }

    /**
     * 解法三：贪婪
     */
    public int cuttingRope_greedy(int n) {

        if(n == 2) {
            return 1;
        } else if(n == 3) {
            return 2;
        }

        int timesOf3 = n / 3;

        if(n - timesOf3 * 3 == 1) {
            timesOf3--;
        }

        int timesOf2 = (n - timesOf3 * 3) / 2;

        return (int) Math.pow(3, timesOf3) * (int) Math.pow(2, timesOf2);

    }

}
