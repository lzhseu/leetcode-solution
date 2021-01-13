package leetcode.算法.动态规划;

/**
 * @author lzh
 * @date 2020/12/27 20:56
 */
public class 比特位计数_338 {

    /**
     * 方法一：位运算
     * 3 ms 23.46%    42.2 MB 95.03%
     */
    public int[] countBits1(int num) {

        int[] res = new int[num + 1];

        for(int i = 0; i <= num; i++) {

            int n = i;
            while(n > 0) {

                res[i] += (n & 1);
                n = n >> 1;
            }
        }

        return res;
    }


    /**
     * 方法二：动态规划
     * 1ms 99.98%    42.7MB 41.27%
     */
    public int[] countBits2(int num) {

        int[] res = new int[num + 1];

        for(int i = 1; i <= num; i++) {

            res[i] = res[i >> 1] + (i & 1);
        }

        return res;
    }

    /**
     * 动态规划的另一种写法
     */
    public int[] countBits3(int num) {

        int[] res = new int[num + 1];

        for(int i = 1; i <= num; i++) {

            if((i & 1) == 1) {
                res[i] = res[i - 1] + 1;
            } else {
                res[i] = res[i >> 1];
            }
        }

        return res;
    }
}
