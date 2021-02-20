package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/18 15:36
 */
public class _53_1_在排序数组中查找数字I {

    /*
     * 二分查找：找左边界和右边界
     */


    /**
     * 写法一：比较冗长。找到目标后，判断前一个或后一个是否还是等于目标值，是的话缩小范围继续查找
     * 0 ms 00.00%    41.6 MB 5.74%
     */
    public int search1(int[] nums, int target) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        }


        int last = getLastK(nums, target, 0, len - 1);
        int first = getFirstK(nums, target, 0, len - 1);

        if(last != -1 && first != -1) {
            return last - first + 1;
        } else {
            return 0;
        }
    }


    private int getFirstK(int[] nums, int target, int left, int right) {

        if(left > right) {
            return -1;
        }

        int mid = left + ((right - left) >> 1);

        if(nums[mid] == target) {
            if((mid - 1 >= 0 && nums[mid - 1] != target) || mid == 0) {
                return mid;
            } else {
                right = mid - 1;
            }
        } else if(nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }

        return getFirstK(nums, target, left, right);
    }

    private int getLastK(int[] nums, int target, int left, int right) {

        if(left > right) {
            return -1;
        }

        int mid = left + ((right - left) >> 1);

        if(nums[mid] == target) {
            if((mid + 1 < nums.length && nums[mid + 1] != target) || mid == nums.length - 1) {
                return mid;
            } else {
                left = mid + 1;
            }
        } else if(nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }

        return getLastK(nums, target, left, right);
    }


    /**
     * 写法二：寻找 target 和 target + 1 的左边界（推荐）
     *
     * 0 ms 100.00%    41.3 MB 71.88%
     */
    public int search2(int[] nums, int target) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        }

        int first = binarySearch(nums, target, 0, len);
        int last = binarySearch(nums, target + 1, 0, len);

        if(first == len || nums[first] != target) {
            return 0;
        }

        return last - first;
    }


    private int binarySearch(int[] nums, int target, int left, int right) {

        while(left < right) {

            int mid = left + ((right - left) >> 1);

            if(nums[mid] >= target) {
                right = mid;
            } else if(nums[mid] < target) {
                left = mid + 1;
            }
        }

        return left;
    }

}
