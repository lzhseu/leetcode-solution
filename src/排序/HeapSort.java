package 排序;

/**
 * @author lzh
 * @date 2020/9/21 11:33
 */
public class HeapSort {

    public static <T extends Comparable<? super T>> void sort(T[] a) {

        int n = a.length;

        // 构造最大堆
        for (int i = n / 2; i >= 1; i--) {
            sink(a, i, n);
        }

        // 删除最大元素，也即每次将最大元素放在数组尾部
        while (n > 1) {
            exchange(a, 1, n);
            sink(a, 1, --n);
        }
    }

    private static <T extends Comparable<? super T>> void sink(T[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(a, j, j + 1)) {
                j++;
            }
            if (less(a, j, k)) {
                break;
            }
            exchange(a, k, j);
            k = j;
        }
    }

    private static <T extends Comparable<? super T>> boolean less(T[] a, int i, int j) {
        return a[i - 1].compareTo(a[j - 1]) < 0;
    }

    private static <T extends Comparable<? super T>> void exchange(T[] a, int i, int j) {
        T tmp = a[i - 1];
        a[i - 1] = a[j - 1];
        a[j - 1] = tmp;
    }

    public static void main(String[] args) {
        Integer[] a = {34,3,100,58,1,2,15,46,27,6,6,32};
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
