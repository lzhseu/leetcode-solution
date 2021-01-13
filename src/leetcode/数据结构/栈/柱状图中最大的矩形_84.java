package leetcode.数据结构.栈;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/6 18:20
 */
public class 柱状图中最大的矩形_84 {

    /**
     * 常规解法（暴力）
     * 731ms 27.49%
     */
    public int largestRectangleArea1(int[] heights) {

        int len;
        if(heights == null || (len = heights.length) == 0) {
            return 0;
        }

        int max = 0;

        // 暴力解法：由当前位置向两边扩展

        for(int i = 0; i < len; i++) {

            int currHeight = heights[i];

            // 往两边扩展
            int count = 0;
            int j = i - 1;
            while(j >= 0 && heights[j] >= currHeight) {
                count++;
                j--;
            }

            j = i + 1;
            while(j < len && heights[j] >= currHeight) {
                count++;
                j++;
            }

            max = Math.max(max, currHeight * (count + 1));

        }

        return max;
    }

    /**
     * 使用栈
     * 10ms 91.04%
     */
    public int largestRectangleArea2(int[] heights) {

        int len;
        if(heights == null || (len = heights.length) == 0) {
            return 0;
        }

        int max = 0;

        // 使用单调栈优化
        // 存储的是下标，因为根据下标可以得到高度
        Deque<Integer> stack = new LinkedList<>();

        // 左右边界使用哨兵
        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[0] = -1;
        newHeights[len + 1] = -1;

        // 加入左边哨兵
        stack.addLast(0);

        for(int i = 1; i < len + 2; i++) {

            int currHeight = newHeights[i];

            // 当前柱子的高度比栈顶元素小的话，需要处理
            while(currHeight < newHeights[stack.peekLast()]) {

                // 待处理的栈顶的高度
                int dealHeight = newHeights[stack.removeLast()];
                int dealWidth = i - stack.peekLast() - 1;
                max = Math.max(max, dealHeight * dealWidth);

            }

            stack.addLast(i);

        }

        return max;
    }

    @Test
    public void test() {
        System.out.println(largestRectangleArea2(new int[]{3, 1, 3, 2, 2}));
    }
}
