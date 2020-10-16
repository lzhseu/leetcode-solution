package 排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/19 19:53
 */
public class BubbleSort {

    public static <T extends Comparable<? super T>> void sort(T[] a) {

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j].compareTo(a[j + 1]) > 0) {
                    T tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }

    @Test
    public void test() {
        Integer[] a = {1,3,100,58,34,2,15,46,27,6,6,32};
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
