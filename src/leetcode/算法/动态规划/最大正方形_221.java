package leetcode.算法.动态规划;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2020/12/24 12:18
 */
public class 最大正方形_221 {

    /**
     * 单调栈做法
     * 6 ms 80.85%    41.1 MB 98.14%
     */
    public int maximalSquare1(char[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];

        int maxSquare = 0;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == '1') {
                    heights[j] = heights[j] + 1;
                } else {
                    heights[j] = 0;
                }

            }

            maxSquare = Math.max(maxSquare, largestSquare(heights));
        }

        return maxSquare;
    }

    private int largestSquare(int[] heights) {

        int maxSquare = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        int len = heights.length;

        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);
        newHeights[0] = -1;
        newHeights[len + 1] = -1;

        dq.addLast(0);

        for(int i = 1; i < len + 2; i++) {

            while(newHeights[i] < newHeights[dq.peekLast()]) {

                int h = newHeights[dq.removeLast()];
                int w = i - dq.peekLast() - 1;
                int a = Math.min(h, w);
                maxSquare = Math.max(maxSquare, a * a);
            }

            dq.addLast(i);
        }

        return maxSquare;
    }

    /**
     * 动态规划
     * 6 ms 80.85%    41.6 MB 77.27%
     */
    public int maximalSquare2(char[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int maxEdge = 0;

        int[][] dp = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {

                if(matrix[i][j] == '1') {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                    }

                    maxEdge = Math.max(maxEdge, dp[i][j]);
                }

            }
        }

        return maxEdge * maxEdge;
    }
}
