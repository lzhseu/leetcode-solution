package leetcode.算法.回文串;

/**
 * @author lzh
 * @date 2020/8/19 11:17
 */
public class 回文子串_647 {

    /**
     * 方法一：动态规划
     * 15 ms 31.99%      39.8 MB 35.70%
     */
    public int countSubstrings1(String s) {

        int len;
        if(s == null || (len = s.length()) == 0) {
            return 0;
        }

        int res = len;
        boolean[][] dp = new boolean[len][len];

        for(int j = 1; j < len; j++) {
            for(int i = 0; i < j; i++) {
                if(j - i < 3 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                } else if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                }

                if(dp[i][j]) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 方法二：中心扩散法
     * 3 ms 91.69%      38.1 MB 50%
     */
    public int countSubstrings2(String s) {

        int len;
        if(s == null || (len = s.length()) == 0) {
            return 0;
        }

        int res = 0;

        for(int i = 0; i < len; i++) {
            res += helper(s, i, i);
            res += helper(s, i, i+1);
        }

        return res;
    }

    private int helper(String s, int left, int right) {
        int res = 0;
        while(left >= 0 && right < s.length()) {
            if(s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                res++;
            } else {
                break;
            }
        }
        return res;
    }
}
