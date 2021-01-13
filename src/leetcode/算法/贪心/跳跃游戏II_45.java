package leetcode.算法.贪心;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/12/15 15:26
 */
public class 跳跃游戏II_45 {

    /**
     * 动态规划
     * 505 ms 5.09%    40.5 MB 71.14%
     */
    public int jump1(int[] nums) {

        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, len);
        dp[0] = 0;

        for(int i = 0; i < len - 1; i++) {

            int step = nums[i];
            for(int j = 1; j <= step; j++) {
                if(i + j < len) {
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }

        return dp[len - 1];
    }

    /**
     * 贪心。挺难想的，挺巧妙的，挺牛逼的
     * 参考：https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247485087&idx=1&sn=ddbed992e5ad8f1aa3b3d4afcb17889b&chksm=9bd7f897aca071817d3ea77acf4a8bc8e277bd38a43ebe2ceba2b42c3184886e07775a628fc7&scene=21#wechat_redirect
     * 2 ms 94.85%    40.4 MB 81.90%
     */
    public int jump(int[] nums) {

        int len = nums.length;
        int count = 0;
        int end = 0;
        int fastest = 0;

        for(int i = 0; i < len - 1; i++) {

            fastest = Math.max(fastest, i + nums[i]);
            if(end == i) {
                count++;
                end = fastest;
            }
        }

        return count;
    }
}
