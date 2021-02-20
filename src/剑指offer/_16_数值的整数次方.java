package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/14 11:16
 */
public class _16_数值的整数次方 {

    public double myPow(double x, int n) {

        if(x == 0 && n < 0) {
            throw new RuntimeException("invalid input");
        }

        // 0 的 0 次方应该怎么处理？
        ///////////////////////////////

        long unsignedN = n;
        unsignedN = Math.abs(unsignedN);

        double res = powWithUnsignedExp(x, unsignedN);

        return n < 0 ? 1 / res : res;

    }

    private double powWithUnsignedExp(double x, long n) {

        if(n == 0) {
            return 1;
        } else if(n == 1) {
            return x;
        }

        double res = powWithUnsignedExp(x, n >> 1);
        res *= res;

        if((n & 1) == 1) {
            res *= x;
        }

        return res;
    }
}
