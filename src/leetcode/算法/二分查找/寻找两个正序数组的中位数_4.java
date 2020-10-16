package leetcode.算法.二分查找;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/26 16:09
 */
public class 寻找两个正序数组的中位数_4 {

    /**
     * 转换为寻找第 k 小的元素
     * 代码设计的很棒，值得学习
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = 0, len2 = 0, len = 0;

        if(nums1 == null || (len1 = nums1.length) == 0) {
            len = nums2.length;
            return len % 2 == 0 ? (double) (nums2[(len - 1) / 2] + nums2[(len - 1) / 2 + 1]) / 2 : nums2[(len - 1) / 2];
        } else if(nums2 == null || (len2 = nums2.length) == 0) {
            len = nums1.length;
            return len % 2 == 0 ? (double) (nums1[(len - 1) / 2] + nums1[(len - 1) / 2 + 1]) / 2 : nums1[(len - 1) / 2];
        }

        len = len1 + len2;
        int midIdx = ((len + 1) >> 1);

        if(len % 2 == 0) {
            return (getKthElement(nums1, nums2, midIdx) + getKthElement(nums1, nums2, midIdx + 1)) / 2;
        } else {
            return getKthElement(nums1, nums2, midIdx);
        }

    }

    public double getKthElement(int[] nums1, int[] nums2, int k) {

        int len1 = nums1 == null ? 0 : nums1.length;
        int len2 = nums2 == null ? 0 : nums2.length;

        int index1 = 0, index2 = 0;

        while(true) {

            // 数组边界情况
            if(index1 == len1) {
                return nums2[index2 + k - 1];
            } else if(index2 == len2) {
                return nums1[index1 + k - 1];
            }

            // 当 k == 1 时，说明只剩最后一个元素没取了，该元素就是第 k 个
            if(k == 1) {
                return (double) Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况下
            int half = k / 2;
            int newIdx1 = Math.min(index1 + half, len1) - 1;
            int newIdx2 = Math.min(index2 + half, len2) - 1;

            if(nums1[newIdx1] <= nums2[newIdx2]) {
                k -= (newIdx1 - index1 + 1);
                index1 = newIdx1 + 1;
            } else {
                k -= (newIdx2 - index2 + 1);
                index2 = newIdx2 + 1;
            }
        }

    }

    @Test
    public void test() {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        double res = findMedianSortedArrays(nums1, nums2);
        System.out.println(res);

        int[] nums21 = {1,2};
        int[] nums22 = {3};
        System.out.println(findMedianSortedArrays(nums21, nums22));

        int[] nums31 = {1,2,3,4,5,10,11};
        int[] nums32 = {1,5,6,7,13,14,15};
        System.out.println(findMedianSortedArrays(nums31, nums32));

        int[] nums41 = {1,2};
        int[] nums42 = {-1,3};
        System.out.println(findMedianSortedArrays(nums41, nums42));
    }
}
