package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/19 10:50
 */
public class _57_1_和为s的两个数字 {

    public int[] twoSum(int[] nums, int target) {

        int len;
        if(nums == null || ((len = nums.length) < 2)) {
            return new int[] {};
        }

        int p1 = 0, p2 = len - 1;
        while(p1 < p2) {
            int sum = nums[p1] + nums[p2];
            if(sum == target) {
                return new int[] {nums[p1], nums[p2]};
            } else if(sum < target) {
                p1++;
            } else {
                p2--;
            }
        }

        return new int[] {};
    }
}
