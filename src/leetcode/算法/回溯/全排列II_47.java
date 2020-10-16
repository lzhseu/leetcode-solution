package leetcode.算法.回溯;

import java.util.*;

/**
 * @author lzh
 * @date 2020/9/18 16:00
 */
public class 全排列II_47 {

    public List<List<Integer>> permuteUnique(int[] nums) {

        int len;
        List<List<Integer>> resList = new ArrayList<>();

        if(nums == null || (len = nums.length) == 0) {
            return resList;
        }

        Arrays.sort(nums);

        Deque<Integer> tmpQue = new ArrayDeque<>();
        boolean[] visited = new boolean[len];

        helper(resList, tmpQue, nums, visited);

        return resList;
    }

    private void helper(List<List<Integer>> resList, Deque<Integer> tmpQue, int[] nums, boolean[] visited) {

        if(tmpQue.size() == nums.length) {
            resList.add(new ArrayList<Integer>(tmpQue));
            return;
        }

        for(int i = 0; i < nums.length; i++) {

            if(visited[i]) {
                continue;
            }

            // 这个剪枝可以的！
            if(i > 0 && !visited[i-1] && nums[i] == nums[i-1]) {
                continue;
            }

            tmpQue.addLast(nums[i]);
            visited[i] = true;
            helper(resList, tmpQue, nums, visited);
            tmpQue.removeLast();
            visited[i] = false;
        }
    }


}
