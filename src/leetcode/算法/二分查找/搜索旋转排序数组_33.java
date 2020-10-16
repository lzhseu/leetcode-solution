package leetcode.算法.二分查找;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/10/16 15:41
 */
public class 搜索旋转排序数组_33 {

    /**
     * 本质：二分查找
     * 重点：肯定有一边是有序的
     * 不足：代码写的不够优雅
     * 0ms 100%    37.6M 99.94%
     */
    public int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        int mid = -1;

        while(left <= right) {
            mid = left + ((right - left) >> 1);
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {

                if(nums[left] < nums[mid]) {
                    // 左边有序
                    left = mid + 1;
                } else {
                    // 右边有序
                    if(target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            } else {
                if(nums[left] <= nums[mid]) {
                    // 左边有序
                    if(nums[left] <= target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    // 右边有序
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 更优雅更易懂的写法
     */
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = -1;

        while(left <= right) {
            mid = left + ((right - left) >> 1);
            if(nums[mid] == target) {
                return mid;
            }

            if(nums[left] <= nums[mid]) {
                // 左边有序
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 右边有序
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2, 3};
        int[] nums = {1};
        int res = search(nums, 0);
        System.out.println(res);
    }
}
