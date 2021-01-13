package leetcode.算法.动态规划;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lzh
 * @date 2020/12/18 10:49
 */
public class 单词拆分_139 {
    public boolean wordBreak(String s, List<String> wordDict) {

        int strLen = s.length();
        if(strLen == 0) {
            return false;
        }

        boolean[] dp = new boolean[strLen + 1];
        dp[0] = true;
        Set<String> wordSet = new HashSet<>(wordDict);

        for(int i = 1; i <= strLen; i++) {

            for(int j = 0; j < i; j++) {

                if(dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[strLen];
    }
}
