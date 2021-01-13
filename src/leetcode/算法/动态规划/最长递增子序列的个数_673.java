package leetcode.算法.动态规划;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/12/2 11:11
 */
public class 最长递增子序列的个数_673 {

    public int findNumberOfLIS(int[] nums) {
        // dp[i] 以 nums[i] 结尾的目标子序列（最长递增子序列）的长度是 dp[i]
        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        }

        int[] dp = new int[len];
        int[] count = new int[len];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    if(dp[j] >= dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if(dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
        }

        int longest = 0, ans = 0;
        for (int length : dp) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < len; ++i) {
            if (dp[i] == longest) {
                ans += count[i];
            }
        }
        return ans;

    }

}
