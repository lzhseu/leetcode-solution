package leetcode.算法.动态规划;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/7/15 9:50
 */
public class 不同的二叉搜索树_96 {

    /**
     * 0ms  100%   36.3M  7.69%
     */
    public int numTrees(int n) {

        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            int tmp = 0;
            for(int j = 1; j <= i; j++) {
                int lf = j - 1;
                int rh = i - j;
                tmp += (dp[lf] * dp[rh]);
            }
            dp[i] = tmp;
        }

        return dp[n];
    }

    /**
     * 进一步优化时间为原来的 1/2
     */
    public int numTrees2(int n) {

        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            int tmp = 0;
            int tmp2 = 0;
            for(int j = 1; j <= (i+1)/2; j++) {
                int lf = j - 1;
                int rh = i - j;
                tmp2 = dp[lf] * dp[rh];
                tmp += (tmp2 * 2);
            }

            dp[i] = i%2 == 1 ? tmp-tmp2 : tmp;
        }

        return dp[n];
    }

    @Test
    public void test() {
        System.out.println(numTrees2(5));
    }
}
