package leetcode.算法.堆;

import java.util.*;

/**
 * @author lzh
 * @date 2020/9/21 18:05
 */
public class 前K个高频元素_347 {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));


        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {

            if(queue.size() < k) {
                queue.offer(entry);
            } else if(queue.size() == k) {
                Map.Entry<Integer, Integer> min = queue.peek();
                if(min.getValue() < entry.getValue()) {
                    queue.poll();
                    queue.offer(entry);
                }
            }
        }

        int[] resArr = new int[k];
        for(int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            resArr[i] = entry.getKey();
        }

        return resArr;
    }
}
