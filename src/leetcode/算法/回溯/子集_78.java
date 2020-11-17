package leetcode.算法.回溯;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lzh
 * @date 2020/11/5 10:22
 */
public class 子集_78 {

    /**
     * 方法一：回溯
     * 1ms 99.14%    38.8M 86.17%
     */
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> resList = new ArrayList<>();
        resList.add(new ArrayList<>());
        if(nums == null || nums.length == 0) {
            return resList;
        }

        Deque<Integer> path = new ArrayDeque<>();

        helper(resList, path, nums, 0);

        return resList;
    }

    private void helper(List<List<Integer>> resList, Deque<Integer> path, int[] nums, int index) {

        for(int i = index; i < nums.length; i++) {

            path.addLast(nums[i]);
            helper(resList, path, nums, i + 1);
            resList.add(new ArrayList<>(path));
            path.removeLast();
        }
    }

    /**
     * 方法二：位运算
     * 1ms    38.7M
     */
    public List<List<Integer>> subsets2(int[] nums) {

        // 方法二：位运算
        // 000 001 010 ... 111
        // 对于每种情况，判断某位是否为1，是则加入子集
        int len;
        List<List<Integer>> resList = new ArrayList<>();
        resList.add(new ArrayList<>());
        if(nums == null || (len = nums.length) == 0) {
            return resList;
        }

        List<Integer> subList = new ArrayList<>();

        for(int mask = 1; mask < (1 << len); mask++) {

            subList.clear();

            for(int i = 0; i < len; i++) {
                if((mask & (1 << i)) != 0) {
                    subList.add(nums[i]);
                }
            }

            resList.add(new ArrayList<>(subList));
        }

        return resList;
    }
}
