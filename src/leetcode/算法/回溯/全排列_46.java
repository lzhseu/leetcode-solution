package leetcode.算法.回溯;

import java.util.*;

/**
 * @author lzh
 * @date 2020/10/19 18:56
 */
public class 全排列_46 {

    /**
     * 2ms 81.73%    38.3M 99.92%
     */
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> resList = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return resList;
        }

        boolean[] visited = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();

        helper(nums, resList, path, visited);

        return resList;
    }

    private void helper(int[] nums, List<List<Integer>> resList, Deque<Integer> path, boolean[] visited) {

        // 终止条件
        if(path.size() == nums.length) {
            resList.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0; i < nums.length; i++) {

            if(visited[i]) {
                continue;
            }
            visited[i] = true;
            path.addLast(nums[i]);
            helper(nums, resList, path, visited);
            path.removeLast();
            visited[i] = false;
        }
    }
}
