package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/9/27 19:26
 */
public class 正则表达式匹配_10 {

    public boolean isMatch(String s, String p) {

        int lens = s.length(), lenp = p.length();

        boolean[][] dp = new boolean[lens + 1][lenp + 1];
        dp[0][0] = true;  // 空串可以匹配成功

        for(int i = 0; i <= lens; i++) {
            for(int j = 1; j <= lenp; j++) {
                if(p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if(isMatches(s, p, i, j - 1)) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else {
                    if(isMatches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[lens][lenp];
    }

    private boolean isMatches(String s, String p, int i ,int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
