package leetcode.算法.动态规划;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lzh
 * @date 2020/7/14 9:33
 */
public class 三角形最小路径和_120 {

    /**
     * 动态规划
     * 但是我的编码总是不好，细节没处理好，导致时间较多 4ms 30+%
     */
    public int minimumTotal1(List<List<Integer>> triangle) {

        if(triangle == null || triangle.size() == 0) {
            return 0;
        }

        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int res = dp[0][0] = triangle.get(0).get(0);


        for(int i = 1; i < n; i++) {
            int tmp = Integer.MAX_VALUE;
            for(int j = 0; j < triangle.get(i).size(); j++) {
                if(j-1 >= 0) {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                } else {
                    dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
                }

                if(dp[i][j] < tmp) {
                    tmp = dp[i][j];
                }
            }
            res = tmp;
        }

        return res;
    }

    /**
     * 动态规划，原理一样
     * 但是编码方式好一点  3ms 73.68%
     * 空间复杂度 O(n^2)
     */
    public int minimumTotal2(List<List<Integer>> triangle) {

        if(triangle == null || triangle.size() == 0) {
            return 0;
        }

        int n = triangle.size();
        int[][] dp = new int[n][n];

        dp[0][0] = triangle.get(0).get(0);


        for(int i = 1; i < n; i++) {

            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);

            for(int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
            }

            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }

        int res = dp[n-1][0];
        for(int i = 1; i < n; i++) {
            res = Math.min(res, dp[n-1][i]);
        }
        return res;
    }


    /**
     * 空间优化 O(2n)
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            int curr = i % 2;
            int prev = 1 - curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[(n - 1) % 2][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        return minTotal;
    }


    /**
     * 动态规划自底向上遍历
     * 时间复杂度 O(n^2)；空间复杂度 O(n)
     * 2ms 96.02%
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        if(triangle == null || triangle.size() == 0) {
            return 0;
        }

        int n = triangle.size();
        int[] dp = new int[n+1];

        for(int i = n-1; i >=0; i--) {
            for(int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }


    @Test
    public void test() {
        List<List<Integer>> triangle = new ArrayList<>();
        Integer[] r = {2};
        triangle.add(Arrays.asList(r));
        r = new Integer[] {3,4};
        triangle.add(Arrays.asList(r));
        r = new Integer[] {6,5,7};
        triangle.add(Arrays.asList(r));
        r = new Integer[] {4,1,8,3};
        triangle.add(Arrays.asList(r));


        System.out.println(minimumTotal1(triangle));
    }
}
