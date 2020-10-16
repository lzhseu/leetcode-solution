package leetcode.算法.动态规划;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/7/23 9:23
 */
public class 最小路径和_64 {

    /**
     * 3ms  89.20%     43.6M 25.765
     */
    public int minPathSum(int[][] grid) {

        int rows = grid.length, cols = grid[0].length;
        int[][] dp = new int[rows+1][cols+1];

        Arrays.fill(dp[rows], Integer.MAX_VALUE);
        dp[rows][cols-1] = 0;
        for(int i = 0; i < rows; i++) {
            dp[i][cols] = Integer.MAX_VALUE;
        }


        for(int i = rows-1; i >= 0; i--) {
            for(int j = cols-1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) + grid[i][j];
            }
        }

        return dp[0][0];
    }

    /**
     * 空间优化，变成滚动数组
     * 2ms 98.20%    42.5M 27.27%
     * 为什么反而是时间优化，空间变大呢？？？
     */
    public int minPathSum2(int[][] grid) {

        int rows = grid.length, cols = grid[0].length;
        int[] dp = new int[cols];
        dp[0] = grid[0][0];

        for(int j = 1; j < cols; j++) {
            dp[j] = dp[j-1] + grid[0][j];
        }

        for(int i = 1; i < rows; i++) {
            dp[0] += grid[i][0];
            for(int j = 1; j < cols; j++) {
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }

        return dp[cols-1];
    }
}
