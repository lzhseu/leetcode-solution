package 排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/19 20:15
 */
public class ShellSort {

    /**
     * 使用的序列是：1/2*(3^k - 1)
     * 1,4,13,40,121,364,1093...
     */

    public static <T extends Comparable<? super T>> void sort(T[] a) {

        int h = 1;
        int N = a.length;
        while (h < N / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {

            int j;

            for (int i = h; i < N; i++) {
                T tmp = a[i];
                for (j = i; j >= h && tmp.compareTo(a[j - h]) < 0; j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = tmp;
            }

            h /= 3;
        }
    }

    @Test
    public void test() {
        Integer[] a = {34,3,100,58,1,2,15,46,27,6,6,32};
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
