package leetcode.数据结构.数组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/12/21 19:40
 */
public class 多数元素_169 {

    /**
     * 哈希表
     * 13ms 44M
     */
    public int majorityElement1(int[] nums) {

        int bound = nums.length / 2;

        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            if(count > bound) {
                return num;
            }
            map.put(num, count);
        }

        return -1;
    }

    /**
     * 排序
     * 2ms 41.7M
     */
    public int majorityElement2(int[] nums) {

        Arrays.sort(nums);
        return nums[(nums.length >> 1)];
    }

    /**
     * 摩尔投票
     * 1ms 99.98%    41.6M 85.06%
     */
    public int majorityElement(int[] nums) {

        int ans = nums[0];
        int votes = 1;

        for(int i = 1; i < nums.length; i++) {
            if(ans == nums[i]) {
                votes++;
            } else {

                if(votes > 0) {
                    votes--;
                } else {
                    ans = nums[i];
                }
            }
        }

        return ans;
    }
}
