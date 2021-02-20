package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/15 9:56
 */
public class _21_调整数组顺序使奇数位于偶数前面 {

    /**
     * 双指针 版本1
     *
     * 3 ms  26.73%     46.7 MB  19.19%
     */
    public int[] exchange1(int[] nums) {

        int left = 0, right = nums.length - 1;

        while(left < right) {

            boolean exLeftFlag = false;
            boolean exRightFlag = false;

            if(nums[left] % 2 == 1) {
                left++;
            } else {
                exLeftFlag = true;
            }

            if(nums[right] % 2 == 0) {
                right--;
            } else {
                exRightFlag = true;
            }

            if(exLeftFlag && exRightFlag) {

                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                left++;
                right--;
            }
        }

        return nums;
    }


    /**
     * 双指针 版本2
     *
     * 2 ms 98.49%    46.7 MB  19.04%
     */
    public int[] exchange2(int[] nums) {

        int left = 0, right = nums.length - 1;

        while(left < right) {

            while(left < right && (nums[left] & 1) == 1) {
                left++;
            }

            while(right > left && (nums[right] & 1) == 0) {
                right--;
            }

            if(left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }

        }

        return nums;
    }


}
