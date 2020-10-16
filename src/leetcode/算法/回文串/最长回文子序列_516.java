package leetcode.算法.回文串;

/**
 * @author lzh
 * @date 2020/7/11 14:55
 */
public class 最长回文子序列_516 {

    public int longestPalindromeSubseq(String s) {

        if(s == null) {
            return 0;
        } else if(s.length() < 2) {
            return s.length();
        }

        char[] charArr = s.toCharArray();
        int len = charArr.length;

        int[][] dp = new int[len][len];

        for(int i = len-2; i >= 0; i--) {

            dp[i][i] = 1;

            for(int j = i+1; j < len; j++) {

                if(charArr[i] == charArr[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }

            }
        }

        return dp[0][len-1];

    }

}
