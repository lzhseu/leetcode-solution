package leetcode.算法.动态规划;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lzh
 * @date 2020/12/10 16:04
 */
public class 俄罗斯套娃信封问题_354 {


    public int maxEnvelopes(int[][] envelopes) {

        if(envelopes == null || envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[0] - a2[0] == 0 ? a2[1] - a1[1] : a1[0] - a2[0];
            }
        });

        int len = envelopes.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int ans = dp[0];

        for(int i = 1; i < len; i++) {

            dp[i] = 1;

            for(int j = 0; j < i; j++) {
                if(envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
