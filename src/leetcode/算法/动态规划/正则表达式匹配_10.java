package leetcode.算法.动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/9/27 19:26
 */
public class 正则表达式匹配_10 {

    /**
     * 由底向上迭代
     * 5 ms 63.45%    37 MB 94.07%
     */
    public boolean isMatch(String s, String p) {

        int lens = s.length(), lenp = p.length();

        boolean[][] dp = new boolean[lens + 1][lenp + 1];

        // 空串可以匹配成功
        dp[0][0] = true;

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


    /**
     * 递归实现
     * 8 ms 25.22%    38.7 MB 16.29%
     */
    public boolean isMatch2(String s, String p) {

        Map<String, Boolean> meno = new HashMap<>();

        return isMatch(s, 0, p, 0, meno);

    }

    private boolean isMatch(String s, int si, String p, int pi, Map<String, Boolean> meno) {

        String key = si + "," + pi;
        if(meno.containsKey(key)) {
            return meno.get(key);
        }

        // base case
        if(pi == p.length()) {
            return si == s.length();
        }

        boolean ans;
        boolean isCharMatch = si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.');

        // 因为 * 匹配的是其前面的字符，所以要先确保长度够
        if(pi < p.length() - 1 && p.charAt(pi + 1) == '*') {
            ans = isMatch(s, si, p, pi + 2, meno) || (isCharMatch && isMatch(s, si + 1, p, pi, meno));
        } else {
            ans = isCharMatch && isMatch(s, si + 1, p, pi + 1, meno);
        }

        meno.put(key, ans);
        return ans;
    }
}
