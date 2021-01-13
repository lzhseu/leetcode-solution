package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/21 18:05
 */
public class 乘积最大子数组_152 {

    public int maxProduct(int[] nums) {

        int len = nums.length;

        int dpMin, dpMax;
        dpMin = dpMax = nums[0];

        int ans = nums[0];

        for(int i = 1; i < len; i++) {

            int minMul = dpMin * nums[i];
            int maxMul = dpMax * nums[i];

            dpMin = Math.min(Math.min(nums[i], minMul), maxMul);
            dpMax = Math.max(Math.max(nums[i], minMul), maxMul);

            ans = Math.max(ans, dpMax);
        }

        return ans;
    }
}
