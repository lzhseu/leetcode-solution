package leetcode.数据结构.栈;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/25 11:00
 */
public class 下一个更大元素III_556 {

    /**
     * 使用单调栈
     * 这是一类方法，可以掌握。但是入栈出栈等操作都是要耗费时间的。
     * 1 ms 58.44%    35.1 MB 91.98%
     */
    public int nextGreaterElement(int n) {

        // 1.利用单调栈从后往前寻找第一个降序对
        // 2 5 4 3 1
        // 一直找到 5 2 才发现了第一个降序对，之前的 [1,4][3,4][4,5] 都不是

        // 2.找到之后弹栈，直到找到比该元素小的那个元素的前一个元素【即找到比2大的最小元素】
        // 这里找到 3

        // 3. 交换 2 和 3
        // 3 5 4 2 1

        // 4. 3后面的元素倒转即可

        char[] nums = String.valueOf(n).toCharArray();

        Deque<Integer> deque = new LinkedList<>();
        int len = nums.length;

        int i;
        int swapIdx = -1;

        for(i = len - 1; i >= 0; i--) {

            while(!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                // 找到了第一个降序对
                // 接着弹栈
                swapIdx = deque.removeLast();
            }

            if(swapIdx != -1) {
                // 交换
                char tmp = nums[i];
                nums[i] = nums[swapIdx];
                nums[swapIdx] = tmp;

                break;
            }

            deque.addLast(i);
        }

        // 如果从始至终没有交换，则说明整个序列是降序的
        if(swapIdx == -1) {
            return -1;
        }

        Arrays.sort(nums, i + 1, len);

        // 要注意整数溢出的问题
        try {
            return Integer.valueOf(new String(nums));
        } catch(NumberFormatException e) {
            return -1;
        }

    }

    /**
     * 一样的思路，但是不具体使用栈这种数据结构
     * 0 ms 100.00%    35 MB 93.54%
     */
    public int nextGreaterElement2(int n) {

        char[] nums = String.valueOf(n).toCharArray();
        int len = nums.length;
        int changeIdx = -1;

        for(int i = len - 1; i > 0; i--) {

            //1. 找到第一个递增
            if(nums[i - 1] < nums[i]) {

                changeIdx = i - 1;

                //2. 找出 changeIdx 后比该位置大的最小数
                int swapIdx;
                for(swapIdx = i + 1; swapIdx < len; swapIdx++) {
                    if(nums[swapIdx] <= nums[changeIdx]) {
                        break;
                    }
                }
                swapIdx--;

                //3. 交换 changeIdx 和 swapIdx 的位置
                swap(nums, changeIdx, swapIdx);

                //4. 把 changeIdx 之后的升序排列【这里使用双指针】
                int left = changeIdx + 1, right = len - 1;
                while(left < right) {
                    swap(nums, left, right);
                    left++;
                    right--;
                }

                break;
            }
        }

        if(changeIdx == -1) {
            return -1;
        }

        try {
            return Integer.parseInt(new String(nums));
        } catch(Exception e) {
            return -1;
        }

    }

    private void swap(char[] nums, int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
