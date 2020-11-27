package leetcode.算法.双指针;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/11/19 9:57
 */
public class 移动零_283 {

    /**
     * 快慢指针套路
     */
    public void moveZeroes(int[] nums) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return;
        }

        int slow = 0, fast = 0;
        while(fast < len) {
            if(nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }

        while(slow < len) {
            nums[slow++] = 0;
        }
    }

    public void moveZeroes1(int[] nums) {

        int len = nums.length;
        int j = 0;
        for(int i = 0; i < len; i++) {

            if(nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }

        for(int i = j; i < len; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {

        int len = nums.length;
        int j = 0;
        for(int i = 0; i < len; i++) {

            if(nums[i] != 0) {
                int tmp = nums[j];
                nums[j++] = nums[i];
                nums[i] = tmp;
            }
        }

    }

    @Test
    public void test() {
        char xor = 'a';
        System.out.println(xor);
        System.out.println(xor ^ 'a');
    }
}
