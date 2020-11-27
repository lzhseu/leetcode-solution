package leetcode.数据结构.栈;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/24 17:06
 */
public class 下一个更大元素I_496 {

    /**
     * 单调栈
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int len1 = nums1.length, len2 = nums2.length;

        Map<Integer, Integer> resMap = new HashMap<>(len2);
        Deque<Integer> stack = new LinkedList<>();

        for(int i = len2 - 1; i >= 0; i--) {

            while(!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }

            int ans = stack.isEmpty() ? -1 : stack.peek();
            resMap.put(nums2[i], ans);
            stack.push(nums2[i]);
        }

        for(int i = 0; i < len1; i++) {
            nums1[i] = resMap.get(nums1[i]);
        }

        return nums1;
    }
}
