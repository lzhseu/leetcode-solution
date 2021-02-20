package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/17 16:37
 */
public class _46_把数字翻译成字符串 {

    /**
     * 动态规划
     *
     * 0 ms 100.00%    35.1 MB  76.33%
     */
    public int translateNum(int num) {

        if(num < 10) {
            return 1;
        }

        String str = String.valueOf(num);
        int len = str.length();

        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= len; i++) {

            int pre = str.charAt(i - 2) - '0';
            int curr = str.charAt(i - 1) - '0';

            dp[i] = dp[i - 1];

            if(pre != 0 && pre * 10 + curr < 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[len];
    }
}
