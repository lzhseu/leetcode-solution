package leetcode.算法.动态规划;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/7/12 20:58
 */
public class 爬楼梯_70 {

    /**
     * 方法一：动态规划
     * 状态转移：dp[i] = dp[i-1] + dp[i-2]
     */
    public int climbStairs(int n) {

        if(n == 1) {
            return 1;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    /**
     * 方法二：递归。会超出时间限制
     */
    public int climbStairs2(int n) {
        if(n == 1) {
            return 1;
        } else if(n == 2) {
            return 2;
        }
        return climbStairs2(n-1) + climbStairs2(n-2);
    }

    @Test
    public void test() {

        System.out.println(climbStairs(10));
        System.out.println(climbStairs2(10));

        System.out.println(climbStairs(45));
        System.out.println(climbStairs2(45));
    }
}
