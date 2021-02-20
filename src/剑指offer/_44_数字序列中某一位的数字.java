package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/2/17 11:02
 */
public class _44_数字序列中某一位的数字 {


    /**
     * 找规律
     *
     * 0 ms 100%
     */
    public int findNthDigit(int n) {

        // n <= (10^i - 10^(i - 1)) * i

        if(n < 10) {
            return n;
        }

        int i = 1;
        double bound = 10;
        int value = n;

        while(true) {

            if(value < bound) {
                break;
            } else if(value == bound) {
                // return (value + "").charAt(0) - '0';
                return digitAtIndex(value, 0, i + 1);
            }

            value -= bound;
            ++i;
            bound = Math.pow(10, i - 1) * 9 * i;
        }

        // 到这里一定是 value < bound

        int order = value / i;
        int remain = value % i;

        int num = (int) Math.pow(10, i - 1) + order;
        //return (num + "").charAt(remain) - '0';
        return digitAtIndex(num, remain, i);

    }

    private int digitAtIndex(int num, int index, int i) {

        int indexFromRight = i - index - 1;

        while(indexFromRight > 0) {
            num /= 10;
            indexFromRight--;
        }

        return num % 10;

    }


    @Test
    public void test() {

        System.out.println(findNthDigit(10));
        System.out.println(findNthDigit(11));
        System.out.println(findNthDigit(18));
        System.out.println(findNthDigit(19));
        System.out.println(findNthDigit(200));
    }
}
