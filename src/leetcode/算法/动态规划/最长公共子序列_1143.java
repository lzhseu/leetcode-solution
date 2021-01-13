package leetcode.算法.动态规划;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/12/1 16:15
 */
public class 最长公共子序列_1143 {

    /**
     * 自底向上，没有状态压缩
     * 12 ms 72.14%    41.8 MB 92.50%
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    /**
     * 自顶向下递归
     * 21 ms 5.39%    42.5 MB 30.62%
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int[][] meno = new int[text1.length()][text2.length()];
        for(int[] row : meno) {
            Arrays.fill(row, -1);
        }
        return dp(text1, 0, text2, 0, meno);
    }

    /**
     * dp(s1, i, s2, j) 计算 s1[i..] 和 s2[j..] 的最长公共子序列长度。
     */
    private int dp(String text1, int i, String text2, int j, int[][] meno) {

        if(i == text1.length() || j == text2.length()) {
            return 0;
        }

        // 如果之前计算过，则不重复计算
        if(meno[i][j] != -1) {
            return meno[i][j];
        }

        if(text1.charAt(i) == text2.charAt(j)) {
            meno[i][j] = dp(text1, i + 1, text2, j + 1, meno) + 1;
        } else {
            meno[i][j] = Math.max(dp(text1, i + 1, text2, j, meno), dp(text1, i, text2, j + 1, meno));
        }

        return meno[i][j];
    }
}
