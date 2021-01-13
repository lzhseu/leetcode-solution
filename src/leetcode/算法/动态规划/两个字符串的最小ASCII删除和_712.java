package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/1 17:20
 */
public class 两个字符串的最小ASCII删除和_712 {

    /**
     * 常规版动态规划
     */
    public int minimumDeleteSum1(String s1, String s2) {

        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 1; i <= len1; i++) {
            dp[i][0] = s1.charAt(i - 1) + dp[i - 1][0];
        }
        for(int j = 1; j <= len2; j++) {
            dp[0][j] = s2.charAt(j - 1) + dp[0][j - 1];
        }

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[len1][len2];
    }
}
