package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/18 13:24
 */
public class 组合总和Ⅳ_377 {

    public int combinationSum4(int[] nums, int target) {

        int len = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int i = 1; i <= target; i++) {
            for(int num : nums) {
                if(i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }
}
