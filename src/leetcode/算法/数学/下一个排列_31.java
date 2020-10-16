package leetcode.算法.数学;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/10/5 15:09
 */
public class 下一个排列_31 {
    public void nextPermutation(int[] nums) {

        int len = nums.length;
        int changeIdx = -1;

        for(int i = len - 1; i > 0; i--) {

            // 1.先找出第一个递增对，在 changeIdx 之后的是一个递减序列
            if(nums[i - 1] < nums[i]) {
                changeIdx = i - 1;

                // 2.找出 changeIdx 后比该位置大的最小数
                int minIdx = changeIdx + 1;
                for(int j = changeIdx + 2; j < len; j++) {
                    if(nums[changeIdx] < nums[j]) {
                        minIdx = j;
                    } else {
                        break;
                    }
                }

                // 3.交换 changeIdx 和 minIdx 的位置
                int tmp = nums[changeIdx];
                nums[changeIdx] = nums[minIdx];
                nums[minIdx] = tmp;

                // 4.把 changeIdx 之后的升序排列
                int changCount = (len - changeIdx - 1) / 2;
                for(int k = 0; k < changCount; k++) {
                    tmp = nums[k + changeIdx + 1];
                    nums[k + changeIdx + 1] = nums[len - 1 - k];
                    nums[len - 1 - k] = tmp;
                }
                break;

            }
        }

        if(changeIdx == -1) {
            int changCount = len / 2;
            for(int k = 0; k < changCount; k++) {
                int tmp = nums[k];
                nums[k] = nums[len - 1 - k];
                nums[len - 1 - k] = tmp;
            }
        }

    }

}
