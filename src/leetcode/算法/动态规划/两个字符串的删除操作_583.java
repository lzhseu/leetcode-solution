package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/1 16:59
 */
public class 两个字符串的删除操作_583 {

    /**
     * 常规版动态规划
     * 10 ms 44.46%    39.3 MB 42.74%
     */
    public int minDistance1(String word1, String word2) {
        if(word1 == null && word2 == null) {
            return 0;
        }

        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for(int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                }
            }
        }

        return dp[len1][len2];
    }
}
