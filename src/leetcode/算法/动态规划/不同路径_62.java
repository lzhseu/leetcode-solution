package leetcode.算法.动态规划;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/10/26 10:29
 */
public class 不同路径_62 {

    /**
     * 动态规划（基本版）
     * 1ms 10.55%
     */
    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {

                if(i - 1 >= 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                if(j - 1 >= 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 动态规划（改进版）
     * 0ms 100%
     */
    public int uniquePaths2(int m, int n) {

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + dp[j];
            }
        }

        return dp[n - 1];
    }
}
