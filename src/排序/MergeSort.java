package 排序;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/19 21:19
 */
public class MergeSort {

    /**
     * 自顶向下的归并，递归方式
     */

    public static <T extends Comparable<? super T>> void sort(T[] a, T[] aux) {
        sort(a, aux, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void sort(T[] a, T[] aux, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int mid = lo + ((hi - lo) >> 1);
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static <T extends Comparable<? super T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {

        if (hi + 1 - lo > 0) {
            System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        } else {
            throw new RuntimeException("hi < lo");
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) > 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }


    @Test
    public void test() {
        Integer[] a = {34,3,100,58,1,2,15,46,27,6,6,32};
        Integer[] aux = new Integer[a.length];
        MergeBUSort.sort(a, aux);
//        sort(a, aux);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
