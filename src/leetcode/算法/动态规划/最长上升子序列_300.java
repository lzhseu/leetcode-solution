package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/2 10:56
 */
public class 最长上升子序列_300 {

    /**
     * 动态规划
     * 81ms 6%
     */
    public int lengthOfLIS(int[] nums) {

        // dp[i] 以 nums[i] 结尾的目标子序列（最长递增子序列）的长度是 dp[i]
        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        }

        int[] dp = new int[len];
        int max = 0;

        for(int i = 0; i < len; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }
}
