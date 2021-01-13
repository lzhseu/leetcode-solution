package leetcode.算法.动态规划;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/12/22 18:30
 */
public class 打家劫舍_198 {

    /**
     * 写成这样是有多傻逼啊
     */
    public int rob1(int[] nums) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        } else if(len == 1) {
            return nums[0];
        }

        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = nums[1];

        int[] max = new int[len];
        max[0] = nums[0];
        max[1] = Math.max(max[0], nums[1]);

        for(int i = 2; i < len; i++) {

            dp[i] = nums[i] + max[i - 2];
            max[i] = Math.max(dp[i], max[i - 1]);
        }

        return Math.max(dp[len - 2], dp[len - 1]);

    }

    /**
     * 牛逼的在这
     */
    public int rob2(int[] nums) {

        int n = nums.length;
        // dp[i] = x 表示：
        // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
        // base case: dp[n] = 0
        int[] dp = new int[n + 2];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];

    }

    /**
     * 针对1 的修改
     */
    public int rob3(int[] nums) {

        int n = nums.length;
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return nums[0];
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[n - 1];

    }

    @Test
    public void test() {
        System.out.println(rob2(new int[]{2, 1, 1, 2}));
    }

}
