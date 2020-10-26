package leetcode.算法.分治;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/10/22 10:54
 */
public class 最大子序和_53 {

    /**
     * 方法一：分而治之
     * O(NlogN)    185ms 5.01%
     */
    public int maxSubArray(int[] nums) {
        return forkAndJoin(nums, 0, nums.length - 1);
    }

    private int forkAndJoin(int[] nums, int begin, int end) {

        int maxLeftSum = 0, maxRhtSum = 0;
        int maxLeftBorderSum = Integer.MIN_VALUE, maxRhtBorderSum = Integer.MIN_VALUE;
        int leftBorderSum = 0, rhtBorderSum = 0;

        // 终止条件
        if (begin == end) {
            return nums[begin];
        }

        int border = begin + ((end - begin + 1) >> 1);

        // 左边最大值
        maxLeftSum = forkAndJoin(nums, begin, border - 1);

        // 右边最大值
        maxRhtSum = forkAndJoin(nums, border, end);

        // 边界到左边的最大值
        for (int i = border - 1; i >= 0; i--) {
            leftBorderSum += nums[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        // 边界到右边的最大值
        for (int i = border; i <= end; i++) {
            rhtBorderSum += nums[i];
            if (rhtBorderSum > maxRhtBorderSum) {
                maxRhtBorderSum = rhtBorderSum;
            }
        }

        return Math.max(Math.max(maxLeftSum, maxRhtSum), maxLeftBorderSum + maxRhtBorderSum);
    }


    /**
     * 方法二：动态规划
     * O(N)    1ms
     */
    public int maxSubArray2(int[] nums) {

        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int n : nums) {
            sum += n;
            if (sum > max) {
                max = sum;
            }
            if (sum <= 0) {
                sum = 0;
            }
        }
        return max;
    }


    @Test
    public void test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {-2, -1};
        int max = maxSubArray(nums);
        int max2 = maxSubArray(nums2);
        System.out.println(max);
        System.out.println(max2);
    }
}
