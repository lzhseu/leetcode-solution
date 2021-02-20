package 剑指offer;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2021/1/13 9:56
 */
public class _03_数组中重复的数字 {

    /**
     * 解法1：排序
     * 3 ms 58.44%    46 MB 82.37%
     */
    public int findRepeatNumber_sort(int[] nums) {

        Arrays.sort(nums);

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }

        return -1;
    }

    /**
     * 解法2：哈希
     * 1 ms 85.27%    46.2 MB 66.13%
     */
    public int findRepeatNumber_hash(int[] nums) {

        int[] hash = new int[nums.length];

        for(int n : nums) {
            if(hash[n] == 0) {
                hash[n] = 1;
            } else {
                return n;
            }
        }

        throw new RuntimeException("no found");
    }

    /**
     * 解法3：原地哈希
     * 0 ms 100.00%    46.1 MB 69.80%
     */
    public int findRepeatNumber(int[] nums) {

        for(int i = 0; i < nums.length; i++) {

            while(nums[i] != i) {

                if(nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                int tmp = nums[i];
                nums[i] = nums[tmp];
                nums[tmp] = tmp;
            }
        }
        throw new RuntimeException("no found");
    }
}
