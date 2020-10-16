package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/10/6 16:24
 */
public class 最长有效括号_32 {

    public int longestValidParentheses(String s) {

        if(s == null || s.length() <= 1) {
            return 0;
        }

        char[] charArr = s.toCharArray();
        int len = charArr.length;
        int max = 0;

        int[] dp = new int[len];

        for(int i = 1; i < len; i++) {

            if(charArr[i] == '(') {
                continue;
            }

            if(charArr[i - 1] == '(') {
                dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
            } else if(i - dp[i - 1] > 0 && charArr[i - dp[i - 1] - 1] == '(') {
                dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
            }

            if(dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }
}
