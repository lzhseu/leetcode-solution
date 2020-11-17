package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/11/14 11:24
 */
public class 买卖股票的最佳时机_121 {

    /**
     * 动态规划
     * 1ms 98.68%
     */
    public int maxProfit(int[] prices) {

        int len = prices.length;
        int max = 0;
        int minPrice = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++) {

            if(minPrice > prices[i]) {
                minPrice = prices[i];
            }

            max = Math.max(max, prices[i] - minPrice);

        }

        return max;
    }

    /**
     * 暴力解法
     */
    public int maxProfit1(int[] prices) {

        int len = prices.length;
        int max = 0;
        for(int i = 0; i < len; i++) {
            for(int j = i + 1; j < len; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }

        return max;
    }
}
