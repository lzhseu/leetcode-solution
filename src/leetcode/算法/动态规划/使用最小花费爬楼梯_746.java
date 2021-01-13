package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/21 20:00
 */
public class 使用最小花费爬楼梯_746 {

    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i = 2; i < len; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        return Math.min(dp[len - 2], dp[len - 1]);
    }
}
