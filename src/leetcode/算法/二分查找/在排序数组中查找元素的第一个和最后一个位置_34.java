package leetcode.算法.二分查找;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/10/18 9:25
 */
public class 在排序数组中查找元素的第一个和最后一个位置_34 {

    /**
     * 题目要求时间复杂度是 O(logn)，那么很自然地想到用二分查找
     * 题目中要查找的是相同元素的第一个和最后一个位置，那么只需要使用二分查找分别查找第一个和最后一个即可
     * 这样的时间复杂度是 O(2logn) = O(logn)
     */

    public int[] searchRange(int[] nums, int target) {
        int len;
        if(nums == null || (len = nums.length) == 0) {
            return new int[] {-1, -1};
        }

        int lf = 0, rh = len - 1;
        int lIdx = recursive(nums, target, lf, rh);
        int rIdx = recursive(nums, target + 1, lf, rh);

        if(nums[lIdx] != target) {
            return new int[] {-1, -1};
        }
        rIdx = nums[rIdx] == target ? rIdx : rIdx - 1;
        return new int[] {lIdx, rIdx};
    }

    public int recursive(int[] nums, int target, int lf, int rh) {

        if(rh - lf <= 0) {
            return lf;
        }

        int mid = lf + ((rh - lf) >> 1);

        if(nums[mid] >= target) {
            return recursive(nums, target, lf, mid);
        } else {
            return recursive(nums, target, mid + 1, rh);
        }
    }
}
