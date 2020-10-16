package 排序;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 最大堆实现的优先级队列
 * @author lzh
 * @date 2020/9/21 10:24
 */
public class MaxPQ <T extends Comparable<? super T>> {

    /**
     * 基于堆的完全二叉树
     */
    private Object[] pq;

    /**
     * 优先级队列中的元素个数
     */
    private int num = 0;

    /**
     * 自定义比较器
     */
    private Comparator<? super T> comparator;

    public MaxPQ(int initCapacity) {
        pq = new Object[initCapacity + 1];
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int initCapacity, Comparator<? super T> comparator) {
        this.comparator = comparator;
        pq = new Object[initCapacity + 1];
    }

    public boolean isEmpty() {
        return num == 0;
    }

    public int size() {
        return num;
    }

    @SuppressWarnings("unchecked")
    public T max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return (T) pq[1];
    }

    /**
     * 帮助数组扩容的辅助函数
     */
    private void resize(int capacity) {
        assert capacity > num;
        Object[] temp = new Object[capacity];
        for (int i = 1; i <= num; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * 插入元素
     * @param v
     */
    public void insert(T v) {
        if (num == pq.length - 1) {
            resize(2 * pq.length);
        }
        pq[++num] = v;
        swim(num);
    }

    @SuppressWarnings("unchecked")
    public T delMax() {
        Object max = pq[1];
        exchange(1, num--);
        sink(1);
        pq[num + 1] = null;
        if ((num > 0) && (num == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return (T) max;
    }

    /**
     * 上浮，调整堆结构的辅助函数
     * @param k 新插入队列的元素的索引
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k, k / 2);
            k /= 2;
        }
    }

    /**
     * 下沉，调整堆结构的辅助函数
     * @param k 从索引为 k 的元素开始下沉
     */
    private void sink(int k) {
        while (2 * k <= num) {
            int j = 2 * k;
            if (j < num && less(j, j + 1)) {
                j++;
            }
            if (less(j, k)) {
                break;
            }
            exchange(k, j);
            k = j;
        }
    }

    /**
     * 比较大小
     * @param i 第一个元素索引
     * @param j 第二个元素索引
     */
    private boolean less(int i, int j) {
        T a = (T) pq[i];
        T b = (T) pq[j];
        if (comparator == null) {
            return a.compareTo(b) < 0;
        } else {
            return comparator.compare(a, b) < 0;
        }
    }

    /**
     * 交换两个元素在数组中的位置
     * @param i 第一个元素的索引
     * @param j 第二个元素的索引
     */
    private void exchange(int i, int j) {
        Object tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MaxPQ<String> pq = new MaxPQ<>();
        while (scanner.hasNext()) {
            String item = scanner.next();
            if (!item.equals("-")) {
                pq.insert(item);
            } else if (!pq.isEmpty()) {
                System.out.print(pq.delMax() + " ");
            }
        }
        System.out.println("(" + pq.size() + " left on pq)");
    }
}
