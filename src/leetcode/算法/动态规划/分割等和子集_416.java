package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/11/30 21:07
 */
public class 分割等和子集_416 {

    /**
     * 初始版本
     * 62 ms 15.68%    39 MB 45.39%
     */
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2 == 1) {
            return false;
        }

        sum /= 2;

        // 问题转换为在 nums 中寻找若干元素（子集），使 sum(子集) = sum/2
        // 0-1背包问题
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for(int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j - nums[i - 1] >= 0) {
                    dp[i][j] |= dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * 状态压缩
     * 26 ms 67.47%    37.7 MB 86.60%
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2 == 1) {
            return false;
        }

        sum /= 2;

        int n = nums.length;
        boolean[] dp = new boolean[sum + 1];

        for(int i = 0; i <= n; i++) {
            dp[0] = true;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = sum; j >= 1; j--) {
                if(j - nums[i - 1] >= 0) {
                    dp[j] |= dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }

}
