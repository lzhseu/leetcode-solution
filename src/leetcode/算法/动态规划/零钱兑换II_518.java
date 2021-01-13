package leetcode.算法.动态规划;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/28 10:57
 */
public class 零钱兑换II_518 {

    /**
     * 回溯
     * AC 不过
     */
    public int change1(int amount, int[] coins) {
        helper(amount, coins, 0);
        return sum;
    }

    int sum = 0;
    private void helper(int amount, int[] coins, int index) {
        // base case
         if(amount == 0) {
             sum++;
             return;
         } else if(amount < 0) {
             return;
         }
        for(int i = index; i < coins.length; i++) {
            helper(amount - coins[i], coins, i);
        }
    }



    /**
     * 动态规划，无状态压缩
     * 9 ms 24.28%    44.7 MB 32.94%
     */
    public int change2(int amount, int[] coins) {

        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        for(int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= amount; j++) {
                // 不装进背包
                dp[i][j] = dp[i - 1][j];
                if(j - coins[i- 1] >= 0) {
                    // 装进背包
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[n][amount];
    }

    /**
     * 动态规划，状态压缩、
     * 4ms 35.9M
     */
    public int change3(int amount, int[] coins) {

        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= amount; j++) {
                if(j - coins[i - 1] >= 0) {
                    dp[j] += dp[j - coins[i - 1]];
                }
            }
        }

        return dp[amount];
    }


    @Test
    public void test() {
        //System.out.println(change1(300, new int[]{3, 5, 7, 8, 9, 10, 11}));
        //System.out.println(change(300, new int[]{3, 5, 7, 8, 9, 10, 11}));
    }
}
