package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/10/27 10:18
 */
public class 编辑距离_72 {

    public int minDistance(String word1, String word2) {

        int len1 = word1 == null ? 0 : word1.length();
        int len2 = word2 == null ? 0 : word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for(int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++) {

            for(int j = 1; j <= len2; j++) {

                int modify = word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]) + 1, dp[i - 1][j - 1] + modify);
            }
        }

        return dp[len1][len2];

    }
}
