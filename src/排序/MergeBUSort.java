package 排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/20 18:19
 */
public class MergeBUSort {

    /**
     * 自底向上的归并，使用循环
     */

    public static <T extends Comparable<? super T>> void sort(T[] a, T[] aux) {

        int N = a.length;
        for (int i = 1; i < N; i += i) {
            for (int j = 0; j < N - i; j += i + i) {
                MergeSort.merge(a, aux, j, j + i - 1, Math.min(j+i+i-1, N-1));
            }
        }
    }

}
