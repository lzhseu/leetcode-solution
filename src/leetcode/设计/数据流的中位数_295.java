package leetcode.设计;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author lzh
 * @date 2020/11/27 9:48
 */
public class 数据流的中位数_295 {

    class MedianFinder {

        private PriorityQueue<Integer> small;
        private PriorityQueue<Integer> large;

        public MedianFinder() {
            small = new PriorityQueue<>((a, b) -> b - a);
            large = new PriorityQueue<>();
        }

        public void addNum(int num) {

            if(small.size() == large.size()) {
                large.offer(num);
                small.offer(large.poll());
            } else {
                small.offer(num);
                large.offer(small.poll());
            }
        }

        public double findMedian() {
            if(small.size() == large.size()) {
                return (small.peek() + large.peek()) / 2.0;
            }
            return small.peek();
        }
    }

    @Test
    public void test() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
    }
}
