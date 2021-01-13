package leetcode.数据结构.数组;

/**
 * @author lzh
 * @date 2020/12/26 21:11
 */
public class 缺失的第一个正数_41 {

    public int firstMissingPositive(int[] nums) {

        int len = nums.length;

        for(int i = 0; i < len; i++) {

            while(nums[i] > 0 && nums[i] < len && nums[i] != nums[nums[i] - 1]) {

                int tmp = nums[i];
                nums[i] = nums[tmp - 1];
                nums[tmp - 1] = tmp;
            }

        }

        for(int i = 0; i < len; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }

        return len + 1;
    }
}
