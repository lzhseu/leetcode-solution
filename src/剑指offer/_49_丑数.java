package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/2/17 17:56
 */
public class _49_丑数 {

    /**
     * 版本一
     * 4 ms 19.68%    37.6 MB  40.38%
     */
    public int nthUglyNumber1(int n) {

        if(n <= 0) {
            return 0;
        }

        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        int p2 = 0, p3 = 0, p5 = 0;
        int count = 1;

        while(count < n) {

            int minValue = Math.min(Math.min(uglyNumbers[p2] * 2, uglyNumbers[p3] * 3), uglyNumbers[p5] * 5);
            uglyNumbers[count] = minValue;

            while(uglyNumbers[p2] * 2 <= minValue) {
                p2++;
            }
            while(uglyNumbers[p3] * 3 <= minValue) {
                p3++;
            }
            while(uglyNumbers[p5] * 5 <= minValue) {
                p5++;
            }

            count++;
        }

        return uglyNumbers[n - 1];

    }


    /**
     * 版本二
     * 3 ms 70.78%    37.7 MB 33.98%
     */
    public int nthUglyNumber2(int n) {

        if(n <= 0) {
            return 0;
        }

        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;

        int p2 = 0, p3 = 0, p5 = 0;
        int count = 1;

        while(count < n) {

            int n2 = uglyNumbers[p2] * 2;
            int n3 = uglyNumbers[p3] * 3;
            int n5 = uglyNumbers[p5] * 5;

            int minValue = Math.min(Math.min(n2, n3), n5);
            uglyNumbers[count] = minValue;

            if(n2 == minValue) {
                p2++;
            }
            if(n3 == minValue) {
                p3++;
            }
            if(n5 == minValue) {
                p5++;
            }

            count++;
        }

        return uglyNumbers[n - 1];

    }


    @Test
    public void test() {

        System.out.println(nthUglyNumber2(10));
    }
}
