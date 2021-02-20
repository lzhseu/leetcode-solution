package 排序;

import org.junit.Test;

import java.util.Random;

/**
 * @author lzh
 * @date 2020/9/20 19:11
 */
public class QuickSort {

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static <T extends Comparable<? super T>> void sort(T[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static <T extends Comparable<? super T>> int partition(T[] a, int lo, int hi) {

        T v = a[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (a[++i].compareTo(v) < 0) {
                if (i == hi) {
                    break;
                }
            }
            while (v.compareTo(a[--j]) < 0) {
                if (j == lo) {
                    break;
                }
            }
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

    /**
     * 随机打乱数组
     * @param a
     */
    public static <T extends Comparable<? super T>>   void shuffle(T[] a) {
        if (a == null) {
            throw new IllegalArgumentException("argument is null");
        }

        Random random = new Random(System.currentTimeMillis());

        int n = a.length;
        for (int i = 0; i < n; i++) {
            // i ~ n-1
            int randInt = random.nextInt(n-i) + i;
            T tmp = a[i];
            a[i] = a[randInt];
            a[randInt] = tmp;
        }
    }

    @Test
    public void test() {
        //Integer[] a = {1,3,100,58,34,2,15,46,27,6,6,32};
        //Integer[] a = {100,2,3,4,5,6,15,46,27,6,6,32};
        Integer[] a = {3, 2, 3};

        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
