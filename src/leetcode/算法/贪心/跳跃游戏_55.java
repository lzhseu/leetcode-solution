package leetcode.算法.贪心;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/10/23 19:09
 */
public class 跳跃游戏_55 {


    /**
     * 这是一种巧妙简洁的做法
     * 2ms 80.86%    40.1M 97.97%
     */
    public boolean canJump(int[] nums) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            throw new RuntimeException("输入错误");
        }

        int farthest = 0;
        for(int i = 0; i < len; i++) {
            if(i > farthest) {
                return false;
            } else if(farthest == len - 1) {
                return true;
            }
            farthest = Math.max(farthest, i + nums[i]);
        }
        return true;

    }

    @Test
    public void test() {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));;
    }
}
