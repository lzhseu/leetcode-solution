package leetcode.数据结构.数组;

/**
 * @author lzh
 * @date 2020/12/2 9:57
 */
public class 最长连续递增序列_674 {
    public int findLengthOfLCIS(int[] nums) {

        if(nums == null || nums.length == 0) {
            return 0;
        }

        int res = 1;
        int tmp = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i - 1] < nums[i]) {
                tmp++;
            } else {
                res = Math.max(res, tmp);
                tmp = 1;
            }
        }
        return Math.max(res, tmp);
    }
}
