package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/13 15:36
 */
public class _10_2_青蛙跳台阶问题 {

    /**
     * 与斐波那契数列解法完全相同
     */

    public int numWays(int n) {

        if(n <= 1) {
            return 1;
        }

        int last = 1, llast = 0;

        for(int i = 1; i <= n; i++) {
            int temp = (last + llast) % 1000000007;
            llast = last;
            last = temp;
        }

        return last;
    }
}
