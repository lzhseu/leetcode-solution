package leetcode.算法.动态规划;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/7/23 9:23
 */
public class 地下城游戏_174 {

    public int calculateMinimumHP(int[][] dungeon) {

        int rows = dungeon.length;
        int cols = dungeon[0].length;

        int[][] dp = new int[rows+1][cols+1];

        for(int i = 0; i < rows+1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[rows][cols-1] = dp[rows-1][cols] = 1;

        for(int i = rows-1; i >= 0; --i) {
            for(int j = cols-1; j >=0; --j) {
                int minn = Math.min(dp[i+1][j], dp[i][j+1]);
                dp[i][j] = Math.max(minn-dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }
}
