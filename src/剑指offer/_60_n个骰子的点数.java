package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/2/19 23:45
 */
public class _60_n个骰子的点数 {

    /**
     * 解法一：递归，存在重复计算，时间效率太低 O(6^N)
     * 2369 ms 5.14%    9 MB 5.37%
     */
    public double[] dicesProbability1(int n) {

        if(n < 1) {
            return new double[] {};
        }

        double[] result = new double[6 + (n - 1) * 5];

        recursive(result, n, n, 0);

        double base = Math.pow(6, n);

        for(int i = 0; i < result.length; i++) {
            result[i] /= base;
        }

        return result;
    }

    private void recursive(double[] result, int n, int count, int sum) {

        if(count == 0) {
            result[sum - n]++;
            return;
        }

        for(int i = 1; i <= 6; i++) {

            recursive(result, n, count - 1, sum + i);
        }
    }


    /**
     * 动态规划：dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + dp[i - 1][j - 3] + dp[i - 1][j - 4] + dp[i - 1][j - 5] + dp[i - 1][j - 6]
     *
     * 0 ms 100.00%    38.7 MB 47.53%
     */
    public double[] dicesProbability2(int n) {

        if(n < 1) {
            return new double[] {};
        }

        double[] result = new double[6 + (n - 1) * 5];

        int[] dp = new int[6 * n + 1];
        for(int i = 1; i <= 6; i++) {
            dp[i] = 1;
        }

        for(int i = 2; i <= n; i++) {

            // 列举一个骰子的点数 1~6
            for(int j = 6 * n; j >= 1; j--) {

                // 计算 dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j - 2] + ...+ dp[i - 1][j - 6]
                int tmp = 0;
                for(int k = 1; k <= 6; k++) {

                    if(j - k < 0) {
                        break;
                    }

                    tmp += dp[j - k];

                }
                dp[j] = tmp;
            }
        }

        double base = Math.pow(6, n);

        for(int i = 0; i < result.length; i++) {
             result[i] = dp[i + n] / base;
        }

        return result;
    }



    @Test
    public void test() {
        dicesProbability2(2);
    }
}
