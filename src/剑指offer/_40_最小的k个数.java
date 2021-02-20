package 剑指offer;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author lzh
 * @date 2021/2/16 20:04
 */
public class _40_最小的k个数 {

    /**
     * 方法一：排序。直观但不推荐
     *
     * 7 ms 71.40%    39.7 MB 72.53%
     */
    public int[] getLeastNumbers1(int[] arr, int k) {

        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }


    /**
     * 方法二：分治。跟 39 题一样，只不过要找到第 k 个，而不是第 n/2 个
     *
     * 2 ms  99.19%     40.1 MB  5.17%
     */
    public int[] getLeastNumbers2(int[] arr, int k) {

        // 分治
        if(k == 0) {
            return new int[] {};
        }

        int lo = 0, hi = arr.length - 1;

        int index = partition(arr, lo, hi);
        int bound = k - 1;

        while(index != bound) {

            if(index < bound) {
                lo = index + 1;
                index = partition(arr, lo, hi);
            } else {
                hi = index - 1;
                index = partition(arr, lo, hi);
            }
        }

        return Arrays.copyOf(arr, k);
    }

    private int partition(int[] arr, int lo, int hi) {

        if(lo == hi) {
            return lo;
        }

        int value = arr[lo];
        int i = lo, j = hi + 1;

        while(true) {

            while(arr[++i] < value) {
                if(i == hi) {
                    break;
                }
            }

            while(arr[--j] > value) {
                if(j == lo) {
                    break;
                }
            }

            if(i >= j) {
                break;
            }

            swap(arr, i, j);
        }

        swap(arr, lo, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * 方法三：最大堆 O(NlogK)
     *
     * 14 ms 在所有 Java 41.13%    39.7 MB 70.27%
     */
    public int[] getLeastNumbers(int[] arr, int k) {

        if(k == 0) {
            return new int[] {};
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (i1, i2) -> i2 - i1);

        int count = 0;

        for(int num : arr) {

            if(count < k) {
                pq.add(num);
                count++;
            } else if(num < pq.peek()) {
                pq.poll();
                pq.add(num);
            }
        }

        int[] result = new int[k];
        int i = 0;
        for(int v : pq) {
            result[i++] = v;
        }

        return result;
    }




    @Test
    public void test() {

        int[] arr = new int[]{3, 2, 1};
        getLeastNumbers2(arr, 2);
    }
}
