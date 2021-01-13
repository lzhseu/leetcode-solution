package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/22 19:29
 */
public class 打家劫舍II_213 {

    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n - 2), rob(nums, 1, n - 1));
    }

    public int rob(int[] nums, int start, int end) {

        int dp1 = 0, dp2 = 0;
        int dp_i = 0;

        for(int i = end; i >= start; i--) {
            dp_i = Math.max(dp1, nums[i] + dp2);
            dp2 = dp1;
            dp1 = dp_i;
        }

        return dp_i;
    }
}
