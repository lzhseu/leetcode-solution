package leetcode.算法.双指针;

/**
 * @author lzh
 * @date 2020/11/24 11:33
 */
public class 移动元素_27 {
    public int removeElement(int[] nums, int val) {
        // 快慢指针秒杀
        int len;
        if (nums == null || (len = nums.length) == 0) {
            return 0;
        }

        int slow = 0, fast = 0;

        while (fast < len) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
            fast++;
        }

        return slow;
    }
}
