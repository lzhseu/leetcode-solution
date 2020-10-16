package 排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/19 19:24
 */
public class InsertionSort {

    public static <T extends Comparable<? super T>> void sort(T[] a) {

        int j;

        for (int i = 1; i < a.length; i++) {
            T tmp = a[i];
            for (j = i; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
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
