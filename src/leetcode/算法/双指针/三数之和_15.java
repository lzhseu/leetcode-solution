package leetcode.算法.双指针;

import org.junit.Test;

import java.util.*;

/**
 * @author lzh
 * @date 2020/9/29 16:09
 */
public class 三数之和_15 {

    /**
     * 排序 + 双指针
     */
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> resList = new ArrayList<>();
        if(nums == null || nums.length < 3) {
            return resList;
        }

        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++) {

            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int target = -nums[i];

            int left = i + 1, right = nums.length - 1;

            while(left < right) {
                if(nums[left] + nums[right] == target) {
                    resList.add(Arrays.asList(nums[left], nums[right], nums[i]));

                    left++;
                    right--;
                    while(left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while(left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if(nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return resList;

    }

    /**
     * 回溯：超出时间限制
     */
    public List<List<Integer>> threeSum1(int[] nums) {

        List<List<Integer>> resList = new ArrayList<>();
        if(nums == null || nums.length < 3) {
            return resList;
        }

        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(nums);
        helper(resList, path, nums, 0, 0);
        return resList;
    }

    private boolean helper(List<List<Integer>> resList, Deque<Integer> path, int[] nums, int index, int target) {

        // 终止条件
        if(path.size() == 3) {
            if(target == 0) {
                resList.add(new ArrayList<>(path));
                return true;
            }
            return false;
        }

        for(int i = index; i < nums.length; i++) {

            // 剪枝
            if(i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            path.addLast(nums[i]);
            boolean flag = helper(resList, path, nums, i + 1, target + nums[i]);
            path.removeLast();

            // 剪枝
            if(flag) {
                break;
            }
        }

        return false;
    }


    @Test
    public void test() {
        int[] nums = {-2, 0, 1, 1, 2};
        int[] nums2 = {-1, 0, 1, 2, -1, -4};
        int[] nums3 = {3,0,-2,-1,1,2};
        List<List<Integer>> res = threeSum(nums3);
        for (List<Integer> list : res){
            for (int n : list) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
