package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/18 16:50
 */
public class _53_2_缺失的数字 {

    /*
     * 二分查找
     */

    /**
     * 写法一：套用左边界的模板
     */
    public int missingNumber1(int[] nums) {

        int left = 0, right = nums.length;

        while(left < right) {

            int mid = left + ((right - left) >> 1);

            if(nums[mid] == mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 写法二：寻找左边界的递归写法
     */
    public int missingNumber2(int[] nums) {

        return helper(nums, 0, nums.length);
    }

    private int helper(int[] nums, int left, int right) {

        if(left == right) {
            return left;
        }

        int mid = left + ((right - left) >> 1);

        if(nums[mid] == mid) {
            left = mid + 1;
        } else {
            right = mid;
        }

        return helper(nums, left, right);
    }


}
