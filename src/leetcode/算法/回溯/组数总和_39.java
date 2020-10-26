package leetcode.算法.回溯;

import java.util.*;

/**
 * @author lzh
 * @date 2020/10/18 10:31
 */
public class 组数总和_39 {


    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> resList = new ArrayList<>();

        if(candidates == null || candidates.length == 0) {
            return resList;
        }
            //　前提要对数组排序
        Arrays.sort(candidates);

        Deque<Integer> path = new ArrayDeque<>();
        helper(candidates, target, resList, path, 0);

        return resList;
    }


    /**
     * 常规写法
     * 4ms 54.28%    38.9M 79.69%
     */
    private void helper(int[] candidates, int target, List<List<Integer>> resList, Deque<Integer> path, int index) {

        // 终止条件
        if(target == 0) {
            resList.add(new ArrayList<>(path));
            return;
        } else if(target < 0) {
            return;
        }

        for(int i = index; i < candidates.length; i++) {
            path.addLast(candidates[i]);
            helper(candidates, target - candidates[i], resList, path, i);
            path.removeLast();
        }
    }

    /**
     * 提前预判并剪枝
     * 2ms 99.94%
     */
    private void helper2(int[] candidates, int target, List<List<Integer>> resList, Deque<Integer> path, int index) {

        for(int i = index; i < candidates.length; i++) {

            // 剪枝
            if(target - candidates[i] == 0) {
                path.addLast(candidates[i]);
                resList.add(new ArrayList<>(path));
                path.removeLast();
                break;
            } else if(target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            helper(candidates, target - candidates[i], resList, path, i);
            path.removeLast();
        }
    }
}
