package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/16 21:08
 */
public class _42_连续子数组的最大和 {

    /**
     * 方法一：动态规划
     *
     * 1 ms  98.08%     44.7 MB  92.59%  的用户
     */
    public int maxSubArray1(int[] nums) {

        int len = nums.length;
        int dp = nums[0];
        int maxValue = nums[0];

        for(int i = 1; i < len; i++) {

            dp = Math.max(dp + nums[i], nums[i]);
            maxValue = Math.max(maxValue, dp);
        }

        return maxValue;
    }

}
