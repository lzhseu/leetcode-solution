package leetcode.数据结构.栈;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2020/12/16 9:56
 */
public class 最大矩形_85 {

    /**
     * 解法1：预计算最大宽度，使用柱状图的优化暴力方法
     * 7 ms 68.70%    41.9 MB 42.30%
     */
    public int maximalRectangle1(char[][] matrix) {

        int rows, cols;
        if((rows = matrix.length) == 0 || (cols = matrix[0].length) == 0) {
            return 0;
        }

        int maxArea = 0;

        int[][] width = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            // 填充 width
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == '1') {
                    width[i][j] = j == 0 ? 1 : width[i][j - 1] + 1;
                } else {
                    width[i][j] = 0;
                }

                // 向上扩展行
                int minWidth = width[i][j];

                for(int k = i; k >= 0; k--) {
                    minWidth = Math.min(minWidth, width[k][j]);
                    if(minWidth == 0) {
                        break;
                    }
                    maxArea = Math.max(maxArea, minWidth * (i - k + 1));
                }
            }

        }
        return maxArea;
    }

    /**
     * 解法2：单调栈，转换为 84 题
     * 6 ms 81.42%    41.3 MB 83.12%
     */
    public int maximalRectangle2(char[][] matrix) {

        int rows, cols;
        if((rows = matrix.length) == 0 || (cols = matrix[0].length) == 0) {
            return 0;
        }

        int maxArea = 0;

        int[] heights = new int[cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {

                if(matrix[i][j] == '1') {
                    heights[j] = i == 0 ? 1 : heights[j] + 1;
                } else {
                    heights[j] = 0;
                }
            }

            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;

    }

    private int largestRectangleArea(int[] heights) {

        int maxArea = 0;
        int len = heights.length;

        Deque<Integer> stack = new ArrayDeque<>();

        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[0] = -1;
        newHeights[len + 1] = -1;
        stack.addLast(0);

        for(int i = 1; i < len + 2; i++) {

            while(newHeights[stack.peekLast()] > newHeights[i]) {

                int h = newHeights[stack.removeLast()];
                int w = i - stack.peekLast() - 1;
                maxArea = Math.max(maxArea, h * w);
            }

            stack.addLast(i);
        }

        return maxArea;
    }
}
