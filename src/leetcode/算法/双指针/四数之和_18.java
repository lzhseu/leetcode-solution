package leetcode.算法.双指针;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzh
 * @date 2020/10/5 9:26
 */
public class 四数之和_18 {

    /**
     * 多加了一些剪枝，性能瞬间上升
     * 3ms 99.98%
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {

        int len;
        List<List<Integer>> resList = new ArrayList<>();
        if(nums == null || (len = nums.length) == 0) {
            return resList;
        }

        Arrays.sort(nums);

        for(int first = 0; first < len - 3; first++) {

            if(first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            if(nums[first] + nums[first + 1] + nums[first + 2] + nums[first + 3] > target) {
                break;
            }

            if(nums[first] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) {
                continue;
            }

            int remain = target - nums[first];

            for(int second = first + 1; second < len - 2; second++) {

                if(second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                if(nums[first] + nums[second] + nums[second + 1] + nums[second + 2] > target) {
                    break;
                }

                if(nums[first] + nums[second] + nums[len - 2] + nums[len - 1] < target) {
                    continue;
                }

                int remain2 = remain - nums[second];

                int forth = len - 1;

                for(int third = second + 1; third < len; third++) {

                    if(third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }

                    while(third < forth && nums[third] + nums[forth] > remain2) {
                        forth--;
                    }

                    if(third == forth) {
                        break;
                    }

                    if(nums[third] + nums[forth] == remain2) {
                        resList.add(Arrays.asList(nums[first], nums[second], nums[third], nums[forth]));
                    }
                }
            }


        }

        return resList;
    }

    /**
     * 双指针
     * 21ms 40.79%
     */
    public List<List<Integer>> fourSum1(int[] nums, int target) {

        int len;
        List<List<Integer>> resList = new ArrayList<>();
        if(nums == null || (len = nums.length) == 0) {
            return resList;
        }

        Arrays.sort(nums);

        for(int first = 0; first < len - 3; first++) {

            if(first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }

            int remain = target - nums[first];

            for(int second = first + 1; second < len - 2; second++) {

                if(second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }

                int remain2 = remain - nums[second];

                int forth = len - 1;

                for(int third = second + 1; third < len; third++) {

                    if(third > second + 1 && nums[third] == nums[third - 1]) {
                        continue;
                    }

                    while(third < forth && nums[third] + nums[forth] > remain2) {
                        forth--;
                    }

                    if(third == forth) {
                        break;
                    }

                    if(nums[third] + nums[forth] == remain2) {
                        resList.add(Arrays.asList(nums[first], nums[second], nums[third], nums[forth]));
                    }
                }
            }


        }

        return resList;
    }

    @Test
    public void test() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        List<List<Integer>> lists = fourSum(nums, 0);

    }
}
