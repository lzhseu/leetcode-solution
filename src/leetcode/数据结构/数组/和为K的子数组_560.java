package leetcode.数据结构.数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2021/1/8 14:34
 */
public class 和为K的子数组_560 {

    /**
     * 暴力
     * 1462 ms 17.18%    41.6 MB 19.45%
     */
    public int subarraySum1(int[] nums, int k) {

        int len = nums.length;
        int res = 0;

        for(int i = 0; i < len; i++) {
            int sum = 0;
            for(int j = i; j < len; j++) {
                sum += nums[j];
                if(sum == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 前缀和
     * 31 ms 44.94%    41.9 MB 5.14%
     */
    public int subarraySum(int[] nums, int k) {

        int len = nums.length;
        int res = 0;

        // 保存前缀和和其个数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);

        // 到当前点的路径和
        int currSum = 0;

        for(int i = 0; i < len; i++) {

            currSum += nums[i];

            res += prefixSumCount.getOrDefault(currSum - k, 0);

            prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        }

        return res;
    }
}
