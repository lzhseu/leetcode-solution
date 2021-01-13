package leetcode.数据结构.数组;

/**
 * @author lzh
 * @date 2020/12/24 14:46
 */
public class 除自身以外数组的乘积_238 {

    /**
     * 2 ms 54.34%    51.7 MB 6.41%
     */
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];

        int[] left = new int[n];
        left[0] = 1;
        int[] right = new int[n];
        right[n - 1] = 1;

        for(int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        for(int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for(int i = 0; i < n; i++) {
            output[i] = left[i] * right[i];
        }

        return output;
    }

    /**
     * 优化
     * 1 ms 100.00%    49 MB 52.82%
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] output = new int[n];

        output[0] = 1;
        for(int i = 1; i < n; i++) {
            output[i] = output[i - 1] * nums[i - 1];
        }

        int right = 1;
        for(int i = n - 1; i >= 0; i--) {
            output[i] = output[i] * right;
            right *= nums[i];
        }

        return output;
    }
}
