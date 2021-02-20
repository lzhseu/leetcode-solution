package 剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2021/1/14 20:20
 */
public class _19_正则表达式匹配 {

    /**
     * 方法一：记忆化递归
     *
     * 8 ms 27.25%    38.7 MB 16.44%
     */
    public boolean isMatch(String s, String p) {

        Map<String, Boolean> cache = new HashMap<>();

        return isMatch(s, 0, p, 0, cache);
    }

    private boolean isMatch(String s, int si, String p, int pi, Map<String, Boolean> cache) {

        String key = si + "," + pi;
        if(cache.containsKey(key)) {
            return cache.get(key);
        }

        if(pi == p.length()) {
            return si == s.length();
        }

        boolean answer;
        boolean isCharMatch = (si < s.length()) && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.');

        if(pi + 1 < p.length() && p.charAt(pi + 1) == '*') {

            answer = isMatch(s, si, p, pi + 2, cache) ||
                    (isCharMatch && isMatch(s, si + 1, p, pi, cache));
        } else {
            answer = isCharMatch && isMatch(s, si + 1, p, pi + 1, cache);
        }

        cache.put(key, answer);

        return answer;
    }


    /**
     * 动态规划
     *
     * 5 ms  50.76%    38.5 MB 23.24%
     */
    public boolean isMatch_dp(String s, String p) {

        int slen = s.length();
        int plen = p.length();

        if(slen == 0 && plen == 0) {
            return true;
        } else if(slen != 0 && plen == 0) {
            return false;
        }

        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;

        for(int i = 0; i <= slen; i++) {
            for(int j = 1; j <= plen; j++) {

                if(p.charAt(j - 1) == '*') {

                    // 匹配 0 个
                    dp[i][j] = dp[i][j - 2];

                    // 匹配任意个
                    if(isCharMatch(s, i, p, j - 1)) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else {

                    dp[i][j] = isCharMatch(s, i, p, j) && dp[i - 1][j - 1];
                }
            }
        }

        return dp[slen][plen];
    }

    private boolean isCharMatch(String s, int i, String p, int j) {

        return i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');

    }
}
