package 剑指offer;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2021/2/20 11:22
 */
public class _61_扑克牌中的顺子 {

    /**
     * 写法一：排序＋模拟
     *
     * 1 ms 90.74%    5.8 MB 71.82%
     */
    public boolean isStraight1(int[] nums) {

        // 只有5个数，排序很快的
        Arrays.sort(nums);

        int zeros = 0;

        for(int i = 1; i < nums.length; i++) {

            if(nums[i - 1] == 0) {
                zeros++;
            } else if(nums[i] - nums[i - 1] > 1) {

                int pre = nums[i - 1], curr = nums[i];
                while(curr - pre > 1 && zeros > 0) {
                    zeros--;
                    pre++;
                }

                if(curr - pre > 1) {
                    return false;
                }

            } else if(nums[i] - nums[i - 1] == 0) {
                return false;
            }
        }

        return true;

    }


    /**
     * 写法二：排序＋统计。分别统计0的个数和间隔数
     *
     * 1 ms 90.74%    35.8 MB 5.63%
     */
    public boolean isStraight2(int[] nums) {

        Arrays.sort(nums);

        int zeros = 0, gap = 0;
        int len = nums.length;

        // 统计 0 的个数
        for(int i = 0; i < len && nums[i] == 0; i++) {
            zeros++;
        }

        // 统计数组中的间隔数
        int small = zeros;
        int big = small + 1;
        while(big < len) {

            if(nums[small] == nums[big]) {
                return false;
            }

            gap += nums[big] - nums[small] - 1;
            small = big;
            big++;
        }

        return gap <= zeros;

    }
}
