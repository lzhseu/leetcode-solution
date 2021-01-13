package leetcode.算法.动态规划;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/30 9:16
 */
public class 目标和_494 {

    /**
     * 用回溯来做，即暴力解
     * 624ms
     */
    public int findTargetSumWays1(int[] nums, int S) {

        if(nums.length == 0) {
            return 0;
        }
        recursive(nums, S, 0);
        return res;
    }

    private int res = 0;
    private void recursive(int[] nums, int target, int index) {
        // base case
        if(nums.length == index) {
            if(target == 0) {
                res++;
            }
            return;
        }

        // 选择+
        recursive(nums, target - nums[index], index + 1);

        // 选择-
        recursive(nums, target + nums[index], index + 1);
    }

    /**
     * 使用备忘录优化
     * 121ms
     */
    public int findTargetSumWays2(int[] nums, int S) {
        // 暴力解
        if(nums.length == 0) {
            return 0;
        }
        return dp(nums, S, 0);
    }

    private Map<String, Integer> meno = new HashMap<>();
    private int dp(int[] nums, int target, int index) {
        // base case
        if(nums.length == index) {
            if(target == 0) {
                return 1;
            }
            return 0;
        }

        String key = index + "," + target;
        if(meno.containsKey(key)) {
            return meno.get(key);
        }

        int result = dp(nums, target - nums[index], index + 1) + dp(nums, target + nums[index], index + 1);

        meno.put(key, result);
        return result;
    }



    /**
     * 动态规划
     * 5ms 38M
     */
    public int findTargetSumWays3(int[] nums, int S) {
        // 我觉得很难想到动态规划怎么做
        // 原问题 -> 子集问题 -> 背包问题（详见 note 的链接）

        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        // 这两种情况是不可能出现合法的子集划分的
        if(sum < S || (sum + S) % 2 == 1) {
            return 0;
        }

        return subsets(nums, (sum + S) / 2);
    }

    private int subsets(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];

        // 当 n 是 0 时，即没有物品的话，根本没办法装背包，所以 dp[0][j] = 0
        // 当 target 是 0 时，只有一种选择：什么都不装
        for(int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= target; j++) {
                if(j - nums[i - 1] >= 0) {
                    // 两种选择的结果之和
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    // 背包的空间不足，只能选择不装物品 i
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];
    }

    /**
     * 空间优化
     * 3ms 36M
     */
    private int subsets2(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = target; j >= 0; j--) {
                if(j - nums[i - 1] >= 0) {
                    // 两种选择的结果之和
                    dp[j] = dp[j] + dp[j - nums[i - 1]];
                } else {
                    // 背包的空间不足，只能选择不装物品 i
                    dp[j] = dp[j];
                }
            }
        }

        return dp[target];
    }
}
