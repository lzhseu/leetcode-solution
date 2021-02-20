package 剑指offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2021/1/14 11:32
 */
public class _17_打印从1到最大的n位数 {

    /**
     * LeetCode 上的很简单，因为只考虑了 int 的范围内，没什么参考价值
     */
    public int[] printNumbers_int(int n) {
        int count = 1;
        int i = 0;
        while(i++ < n) {
            count *= 10;
        }
        count -= 1;
        int[] res = new int[count];
        for(i = 0; i < count; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    /**
     * 实际上应该考虑大数问题，即应该使用 String 或 char[] 来存储数字
     */


    /**
     * 方法一：模拟整数加法
     */
    public void printNumbers1(int n) {

        if (n <= 0) {
            return;
        }

        char[] number = new char[n];
        Arrays.fill(number, '0');

        while (!increment(number)) {
            printNumber(number);
        }
    }

    /**
     * 模拟整数加法，值得学习
     */
    private boolean increment(char[] number) {

        boolean isOverflow = false;
        int len = number.length;
        int nTakeOver = 0;

        for (int i = len - 1; i >= 0; i--) {

            // 当前的数字＋进位
            int nSum = number[i] - '0' + nTakeOver;

            // 从最低位开始 +1，其他位只有低位进位了才会 +1
            if (i == len - 1) {
                nSum++;
            }

            if (nSum >= 10) {

                // 如果在最高位发生了进位
                if (i == 0) {
                    isOverflow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = (char) ('0' + nSum);
                }
            } else {
                number[i] = (char) ('0' + nSum);
                break;
            }
        }

        return isOverflow;
    }

    /**
     * 打印
     */
    private void printNumber(char[] number) {

        boolean isBeginning0 = true;

        for (int i = 0; i < number.length; i++) {

            if (isBeginning0 && number[i] != '0') {
                isBeginning0 = false;
            }

            if (!isBeginning0) {
                System.out.print(number[i]);
            }
        }
        System.out.print('\t');
    }


    /**
     * 方法二：把问题转换成数字排列的解法
     */
    void printNumbers2(int n) {

        if (n <= 0) {
            return;
        }

        char[] number = new char[n];

        printNumbersRecursively(number, n, 0);
    }

    void printNumbersRecursively(char[] number, int length, int index) {

        if (index == length) {
            printNumber(number);
            return;
        }

        for (int i = 0; i < 10; i++) {
            number[index] = (char) (i + '0');
            printNumbersRecursively(number, length, index + 1);
        }

    }



    @Test
    public void test() {
//        printNumbers1(3);
        printNumbers2(3);

        char[] number = new char[3];
//        printNumbersRecursively(number, 3, 0);

    }

}
