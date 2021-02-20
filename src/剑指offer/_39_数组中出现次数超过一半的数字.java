package 剑指offer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lzh
 * @date 2021/2/16 10:20
 */
public class _39_数组中出现次数超过一半的数字 {

    /**
     * 排序：直观，但是不推荐
     * O(nlogn)
     *
     * 2 ms  60.44%    41.8 MB  39.98%
     */
    public int majorityElement1(int[] nums) {

        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    /**
     * 分治
     * O(nlogn)
     *
     * 1 ms  100.00%    41.9 MB  36.90%
     */
    public int majorityElement2(int[] nums) {

        return forkJoin(nums, 0, nums.length - 1);

    }

    private int forkJoin(int[] nums, int left, int right) {

        if(left == right) {
            return nums[left];
        }

        int mid = left + ((right - left) >> 1);

        int leftElement = forkJoin(nums, left, mid);
        int rightElement = forkJoin(nums, mid + 1, right);

        if(leftElement == rightElement) {
            return leftElement;
        }

        int leftCount = countInRange(nums, leftElement, left, right);
        int rightCount = countInRange(nums, rightElement, left, right);

        return leftCount > rightCount ? leftElement : rightElement;

    }

    private int countInRange(int[] nums, int value, int left, int right) {

        int count = 0;
        for(int i = left; i <= right; i++) {
            if(nums[i] == value) {
                count++;
            }
        }
        return count;
    }


    /**
     * 摩尔投票：最快
     * O(n)
     *
     */
    public int majorityElement3_1(int[] nums) {

        int count = 1, result = nums[0];

        for(int i = 1; i < nums.length; i++) {

            if(nums[i] == result) {
                count++;
            } else {

                if(count > 0) {
                    count--;
                } else {
                    result = nums[i];
                    count = 1;
                }
            }

        }

        return result;
    }

    public int majorityElement3(int[] nums) {

        int count = 1, result = nums[0];

        for(int i = 1; i < nums.length; i++) {
            if(count == 0) {
                result = nums[i];
                count = 1;
            } else if(nums[i] == result) {
                count++;
            } else {
                count--;
            }
        }

        return result;
    }


    /**
     * 借鉴快排
     */
    public int majorityElement4(int[] nums) {

        // 方法四：借鉴快排

        int len = nums.length;
        int middle = len >> 1;
        int lo = 0, hi = len - 1;
        int index = partition(nums, lo, hi);

        while(index != middle) {
            if(index < middle) {
                lo = index + 1;
                index = partition(nums, lo, hi);
            } else {
                hi = index - 1;
                index = partition(nums, lo, hi);
            }
        }

        return nums[index];
    }


    private int partition(int[] nums, int lo, int hi) {

        if(lo == hi) {
            return lo;
        }

        int index = randomIndex(lo, hi);
        int value = nums[index];

        swap(nums, lo, index);

        int i = lo, j = hi + 1;

        while(true) {

            while(nums[++i] < value) {
                if(i == hi) {
                    break;
                }
            }

            while(value < nums[--j]) {
                if(j == lo) {
                    break;
                }
            }

            if(i >= j) {
                break;
            }

            swap(nums, i, j);

        }

        swap(nums, lo, j);

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private int randomIndex(int lo, int hi) {
        return new Random().nextInt(hi - lo) + lo;
    }



    @Test
    public void test() {

        int[] nums = {2, 1, 2, 1, 3, 2, 4, 2, 2};
        int[] nums2 = {3, 2, 3};

        System.out.println(majorityElement4(nums2));
    }
}
