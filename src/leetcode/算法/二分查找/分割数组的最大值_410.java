package leetcode.算法.二分查找;

/**
 * @author lzh
 * @date 2020/7/25 15:12
 */
public class 分割数组的最大值_410 {

    /**
     * 方法一：二分查找
     * 0ms 100%   37.1M 33.33%
     */
    public int splitArray(int[] nums, int m) {

        //1. 二分查找
        int left = 0, right = 0;
        for(int num : nums) {
            right += num;
            left = Math.max(left, num);
        }

        while(left < right) {
            int mid = left + (right - left) / 2;
            int cnt = calcnt(nums, mid, m);
            if(m < cnt) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int calcnt(int[] nums, int mid, int m) {

        int cnt = 1, sum = 0;

        for(int num: nums) {
            if(sum + num > mid) {
                cnt++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return cnt;
    }


}
