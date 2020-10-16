package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/7/19 8:58
 */
public class 不同路径II_63 {

    /**
     * 0ms  100%   39.2M  48.15%
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[][] dp = new int[rows+1][cols+1];

        if(obstacleGrid[rows-1][cols-1] == 0) {
            dp[rows-1][cols] = 1;
        } else {
            return 0;
        }

        for(int i = rows-1; i >= 0; i--) {
            for(int j = cols-1; j >=0; j--) {
                if(obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }

        return dp[0][0];
    }


    /**
     * 利用滚动数组进行空间优化
     * 0ms 100%    37.5M 88.89%
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {

        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[] dp = new int[cols+1];

        if(obstacleGrid[rows-1][cols-1] == 0) {
            dp[cols] = 1;
        } else {
            return 0;
        }

        for(int i = rows-1; i >= 0; i--) {
            for(int j = cols-1; j >=0; j--) {
                if(obstacleGrid[i][j] == 0) {
                    dp[j] = dp[j] + dp[j+1];
                } else {
                    dp[j] = 0;
                }
            }
            dp[cols] = 0;
        }

        return dp[0];
    }
}
