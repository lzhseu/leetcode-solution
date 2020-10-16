package 排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/19 20:06
 */
public class SelectionSort {

    public static <T extends Comparable<? super T>> void sort(T[] a) {

        for (int i = 0; i < a.length; i++) {

            int minIdx = i;

            for (int j = i + 1; j < a.length; j++) {
                if (a[minIdx].compareTo(a[j]) > 0) {
                    minIdx = j;
                }
            }

            T tmp = a[minIdx];
            a[minIdx] = a[i];
            a[i] = tmp;
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
