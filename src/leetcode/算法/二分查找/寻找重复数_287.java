package leetcode.算法.二分查找;

/**
 * @author lzh
 * @date 2020/12/26 20:17
 */
public class 寻找重复数_287 {

    /**
     * 二分
     */
    public int findDuplicate1(int[] nums) {

        // 左闭右闭
        int left = 0, right = nums.length - 1;

        while(left <= right) {

            int mid = left + ((right - left) >> 1);

            int cnt = 0;
            for(int n : nums) {
                if(n <= mid) {
                    cnt++;
                }
            }

            if(cnt > mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }


    /**
     * 快慢指针：技巧性太强了
     */
    public int findDuplicate2(int[] nums) {

        int slow = 0, fast = 0;

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        slow = 0;

        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
