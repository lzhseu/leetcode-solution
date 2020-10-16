package leetcode.算法.动态规划;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/7/14 21:24
 */
public class 做菜顺序_1402 {

    /**
     * 自己写的
     * 8ms 16.19%   40.1M 100%
     */
    public int maxSatisfaction(int[] satisfaction) {

        int len;
        if(satisfaction == null || (len = satisfaction.length) == 0) {
            return 0;
        }

        Arrays.sort(satisfaction);

        int[][] dp = new int[len][len+1];

        for(int i = 0; i < len; i++) {
            dp[i][1] = satisfaction[i];
            for(int j = 2; j <= i+1; j++) {
                dp[i][j] = dp[i-1][j-1] + j * satisfaction[i];
            }
        }

        int res = 0;
        for(int j = 1; j <= len; j++) {
            res = Math.max(dp[len-1][j], res);
        }
        return res;
    }

    /**
     * 空间优化 O(n^2) -> O(2n)
     * 5ms 23.67%   38.1M 100%
     */
    public int maxSatisfaction2(int[] satisfaction) {

        int len;
        if(satisfaction == null || (len = satisfaction.length) == 0) {
            return 0;
        }

        Arrays.sort(satisfaction);

        int[][] dp = new int[2][len];

        for(int i = 0; i < len; i++) {
            int curr = i % 2;
            int prev = 1 - curr;
            dp[curr][0] = satisfaction[i];
            for(int j = 1; j < i+1; j++) {
                dp[curr][j] = dp[prev][j-1] + (j+1) * satisfaction[i];
            }
        }

        int res = 0;
        for(int j = 0; j < len; j++) {
            res = Math.max(dp[(len-1)%2][j], res);
        }
        return res;
    }

    /**
     * 贪心算法
     * 1ms 100% m   37.7M 100%
     */
    public int maxSatisfaction3(int[] satisfaction) {

        int len;
        if(satisfaction == null || (len = satisfaction.length) == 0) {
            return 0;
        }

        Arrays.sort(satisfaction);
        int res = 0;
        int tmp = 0;
        for(int i = len-1; i >= 0; i--) {
            if(tmp + satisfaction[i] > 0) {
                tmp += satisfaction[i];
                res += tmp;
            } else {
                break;
            }
        }

        return res;
    }

    @Test
    public void test() {
        System.out.println(maxSatisfaction2(new int[] {-1,-8,0,5,-9}));
        System.out.println(maxSatisfaction2(new int[] {4,3,2}));
        System.out.println(maxSatisfaction2(new int[] {-1,-4,-5}));
        System.out.println(maxSatisfaction2(new int[] {-2,5,-1,0,3,-3}));
    }
}
