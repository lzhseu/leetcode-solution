package leetcode.算法.双指针;

/**
 * @author lzh
 * @date 2020/11/24 11:19
 */
public class 删除排序数组中的重复项_26 {

    /**
     * 快慢指针
     */
    public int removeDuplicates(int[] nums) {

        int len;
        if(nums == null || (len = nums.length) == 0) {
            return 0;
        }

        int slow = 0, fast = 1;
        while(fast < len) {
            if(nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
            fast++;
        }

        // 因为返回的是长度
        return slow + 1;
    }
}
