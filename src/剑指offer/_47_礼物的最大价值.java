package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/2/17 16:51
 */
public class _47_礼物的最大价值 {

    /**
     * 动态规划
     *
     * 3 ms  80.31%     41.2 MB  43.37%
     */
    public int maxValue1(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows + 1][cols + 1];


        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= cols; j++) {

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }

        return dp[rows][cols];
    }

    /**
     * 空间压缩
     *
     * 2 ms 97.82%    41.1 MB  68.37%
     */
    public int maxValue2(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[] dp = new int[cols + 1];

        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= cols; j++) {

                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
            }
        }

        return dp[cols];
    }


    @Test
    public void test() {

    }
}
