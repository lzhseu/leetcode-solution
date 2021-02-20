package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/20 18:29
 */
public class _63_股票的最大利润 {

    public int maxProfit(int[] prices) {

        int len;
        if(prices == null || (len = prices.length) < 2) {
            return 0;
        }

        int pre = prices[0];
        int maxProfit = 0;

        for(int i = 1; i < len; i++) {
            if(prices[i] <= pre) {
                pre = prices[i];
            } else {
                maxProfit = Math.max(maxProfit, prices[i] - pre);
            }
        }

        return maxProfit;
    }
}
