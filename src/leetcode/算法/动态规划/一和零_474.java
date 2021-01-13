package leetcode.算法.动态规划;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/12/19 9:48
 */
public class 一和零_474 {

    /**
     * 无状态压缩
     * 71 ms 30.77%    67.4 MB 17.22%
     */
    public int findMaxForm1(String[] strs, int m, int n) {

        int len = strs.length;

        int[][][] dp = new int[len + 1][m + 1][n + 1];

        // base case: dp[0][j][k] = 0


        for(int i = 1; i <= len; i++) {

            String str = strs[i - 1];

            // 计算有多少个 0 和 1
            int zeroCount = 0, oneCount = 0;
            for(int t = 0; t < str.length(); t++) {
                if(str.charAt(t) == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }

            // 状态转移
            for(int j = 0; j <= m; j++) {

                for(int k = 0; k <= n; k++) {

                    // 不选择第 i 个
                    dp[i][j][k] = dp[i - 1][j][k];

                    if(j >= zeroCount && k >= oneCount) {
                        dp[i][j][k] = Math.max(dp[i][j][k], 1 + dp[i - 1][j - zeroCount][k - oneCount]);
                    }

                }
            }
        }

        return dp[len][m][n];
    }

    /**
     * 状态压缩
     * 32 ms 93.68%    37.7 MB 88.59%
     */
    public int findMaxForm(String[] strs, int m, int n) {


        int[][] dp = new int[m + 1][n + 1];

        // base case: dp[0][j][k] = 0

        for(String str : strs) {

            // 计算有多少个 0 和 1
            int zeroCount = 0, oneCount = 0;
            for(int t = 0; t < str.length(); t++) {
                if(str.charAt(t) == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }

            // 状态转移
            for(int j = m; j >= zeroCount; j--) {
                for(int k = n; k >= oneCount; k--) {
                    dp[j][k] = Math.max(dp[j][k], 1 + dp[j - zeroCount][k - oneCount]);
                }
            }
        }

        return dp[m][n];
    }

    @Test
    public void test() {
        System.out.println(findMaxForm(new String[]{"10", "0", "1"}, 1, 1));

    }
}
