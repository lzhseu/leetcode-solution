package leetcode.算法.分治;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/1/5 15:27
 */
public class 戳气球_312 {

    /**
     * 回溯
     * AC 不过
     */
    public int maxCoins1(int[] nums) {

        helper(nums, 0, 0);
        return maxValue;
    }


    private int maxValue = 0;

    private void helper(int[] nums, int count, int currCoins) {

        if(count == nums.length) {
            // 更新最大值
            maxValue = Math.max(maxValue, currCoins);
            return;
        }

        for(int i = 0; i < nums.length; i++) {

            // 如果 nums[i] 被访问过
            if(nums[i] == -1) {
                continue;
            }

            // 否则，选择 nums[i]
            int temp = nums[i];

            // 把它标为已访问
            nums[i] = -1;

            // 要算钱数
            int before = i - 1;
            while(before >= 0 && nums[before] == -1) {
                before--;
            }
            int beforeCoin = before < 0 ? 1 : nums[before];

            int after = i + 1;
            while(after < nums.length && nums[after] == -1) {
                after++;
            }
            int afterCoin = after < nums.length ? nums[after] : 1;

            int tempCoin = beforeCoin * temp * afterCoin;

            // 递归调用
            helper(nums, count + 1, currCoins + tempCoin);

            // 回溯
            nums[i] = temp;
        }
    }


    /**
     * 分治
     */
    public int maxCoins2(int[] nums) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        }

        int[] nums2 = new int[len + 2];
        System.arraycopy(nums, 0, nums2, 1, len);
        nums2[0] = 1;
        nums2[len + 1] = 1;

        int[][] cache = new int[len + 2][len + 2];

        return forkAndJoin(nums2, 0, len + 1, cache);
    }


    private int forkAndJoin(int[] nums, int begin, int end, int[][] cache) {

        if(end - begin < 2) {
            return 0;
        }

        if(cache[begin][end] != 0) {
            return cache[begin][end];
        }

        int maxValue = 0;

        // 取(begin, end)之间的每个值作为中间边界，求取哪个值是最大的
        for(int i = begin + 1; i < end; i++) {

            int temp = forkAndJoin(nums, begin, i, cache) +
                    nums[i] * nums[begin] * nums[end] +
                    forkAndJoin(nums, i, end, cache);

            maxValue = Math.max(maxValue, temp);

        }

        cache[begin][end] = maxValue;

        return maxValue;
    }


    /**
     * 动态规划
     */
    public int maxCoins3(int[] nums) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        }

        int[] nums2 = new int[len + 2];
        System.arraycopy(nums, 0, nums2, 1, len);
        nums2[0] = 1;
        nums2[len + 1] = 1;

        int[][] dp = new int[len + 2][len + 2];

        for(int i = len; i >= 0; i--) {
            for(int j = i + 2; j < len + 2; j++) {

                int maxValue = 0;

                for(int k = i + 1; k < j; k++) {
                    int temp = dp[i][k] + nums2[i] * nums2[k] * nums2[j] + dp[k][j];
                    maxValue = Math.max(maxValue, temp);
                }

                dp[i][j] = maxValue;
            }
        }

        return dp[0][len + 1];
    }


}
