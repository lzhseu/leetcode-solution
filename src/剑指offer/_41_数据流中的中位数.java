package 剑指offer;

import java.util.PriorityQueue;

/**
 * @author lzh
 * @date 2021/2/16 20:57
 */
public class _41_数据流中的中位数 {

    /**
     * 大顶堆和小顶堆
     *
     * 87 ms 37.63%    49.6 MB  52.66%
     */
    static class MedianFinder {

        private PriorityQueue<Integer> bigTopHeap;
        private PriorityQueue<Integer> smallTopHeap;

        /** initialize your data structure here. */
        public MedianFinder() {
            bigTopHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
            smallTopHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {

            if(bigTopHeap.size() == smallTopHeap.size()) {
                bigTopHeap.add(num);
                smallTopHeap.add(bigTopHeap.poll());
            } else {
                smallTopHeap.add(num);
                bigTopHeap.add(smallTopHeap.poll());
            }

        }

        public double findMedian() {

            if(bigTopHeap.size() == smallTopHeap.size()) {
                return (bigTopHeap.peek() + smallTopHeap.peek()) / 2.0;
            } else {
                return (double) smallTopHeap.peek();
            }
        }
    }
}
