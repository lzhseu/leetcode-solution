package leetcode.算法.贪心;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lzh
 * @date 2020/12/14 19:43
 */
public class 无重叠区间_435 {

    public int eraseOverlapIntervals(int[][] intervals) {

        if(intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[1] - a2[1];
            }
        });

        int count = 1;

        int rightBound = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= rightBound) {
                count++;
                rightBound = intervals[i][1];
            }

        }

        return intervals.length - count;
    }

    @Test
    public void test() {
        int result1 = eraseOverlapIntervals(new int[][]{{1, 2}, {2, 5}, {3, 4}, {1, 3}});
        System.out.println(result1);
    }
}
