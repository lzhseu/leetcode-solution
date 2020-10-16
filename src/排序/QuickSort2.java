package 排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/20 20:46
 */
public class QuickSort2 {

    /**
     * 1. 当元素个数 <= 10，使用插入排序
     * 2. 使用哨兵，减少 partition 中的判断（没做）
     */

    public static int insertSortThreshold = 10;

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        QuickSort.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo + insertSortThreshold) {
            insertionSort(a, lo, hi);
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    public static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {

        T v = a[lo];

        int i = lo, j = hi + 1;
        while (true) {
            while (a[++i].compareTo(v) < 0) {}
            while (v.compareTo(a[--j]) < 0) {}
            if (i >= j) {
                break;
            }

            T tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        T tmp = a[j];
        a[j] = a[lo];
        a[lo] = tmp;
        return j;
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] a, int lo, int hi) {

        int j;

        for (int i = lo + 1; i <= hi; i++) {
            T tmp = a[i];
            for (j = i; j > lo && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    @Test
    public void test() {
        Integer[] a = {100,2,3,4,5,6,15,46,27,6,6,32};
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
