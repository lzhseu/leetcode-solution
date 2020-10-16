package leetcode.算法.位运算;

/**
 * @author lzh
 * @date 2020/8/23 10:29
 */
public class 数字范围按位与_201 {

    /**
     * 方法一：每次去掉 n 最右边的 1
     * 6ms 99.82%
     */
    public int rangeBitwiseAnd(int m, int n) {

        while(m < n) {
            n &= (n-1);
        }
        return n;
    }

    /**
     * 方法二：两数每次右移1位，直至相等
     * 6ms 99.82%
     */
    public int rangeBitwiseAnd2(int m, int n) {

        int shift = 0;
        while(m < n) {
            m = (m >> 1);
            n = (n >> 1);
            shift++;
        }
        return n << shift;
    }

}
