package leetcode.算法.排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/10/28 10:43
 */
public class 颜色分类_75 {

    /**
     * 插入排序
     * 1ms 10.31%    36.8M
     */
    public void sortColors1(int[] nums) {
        // 想法1：插入排序
        int j;
        for(int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            for(j = i; j > 0 && tmp < nums[j - 1]; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = tmp;
        }

    }

    /**
     * 希尔排序
     * 0ms 100%    36.8M
     */
    public void sortColors2(int[] nums) {
        // 想法2：希尔排序（改进版插入排序）

        int h = 1;
        int N = nums.length;
        while(h < N / 3) {
            h = 3 * h + 1;
        }

        while(h >= 1) {
            int j;
            for(int i = h; i < N; i++) {
                int tmp = nums[i];
                for(j = i; j >= h && tmp < nums[j - h]; j -= h) {
                    nums[j] = nums[j - h];
                }
                nums[j] = tmp;
            }
            h /= 3;
        }

    }

    /**
     * 没想到的，使用双指针
     * 时间复杂度 O(N) 空间复杂度 O(1)
     * 0ms
     */
    public void sortColors(int[] nums) {

        int p0 = 0, p2 = nums.length - 1;

        for (int i = 0; i <= p2; i++) {

            while (i <= p2 && nums[i] == 2) {
                nums[i] = nums[p2];
                nums[p2] = 2;
                --p2;
            }

            if (nums[i] == 0) {
                nums[i] = nums[p0];
                nums[p0] = 0;
                ++p0;
            }
        }
    }

    @Test
    public void test() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
    }
}
