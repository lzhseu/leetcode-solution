package leetcode.算法.贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lzh
 * @date 2020/12/14 19:19
 */
public class 删除被覆盖区间_1288 {

    /**
     * 用到的排序技巧值得品味
     */
    public int removeCoveredIntervals(int[][] intervals) {

        int result = intervals.length;

        // 按左边界升序排列
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[0] == a2[0] ? a2[1] - a1[1] : a1[0] - a2[0];
            }
        });

        // 只要比较右边界即可
        int rightBound = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            if(intervals[i][1] <= rightBound) {
                result--;
            } else {
                rightBound = intervals[i][1];
            }
        }

        return result;
    }
}
