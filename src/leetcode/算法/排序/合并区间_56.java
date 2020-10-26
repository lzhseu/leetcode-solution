package leetcode.算法.排序;

import java.util.*;

/**
 * @author lzh
 * @date 2020/10/24 11:21
 */
public class 合并区间_56 {

    /**
     * 初始版本
     * 8ms 61%    40.8M 97.28%
     */
    public int[][] merge(int[][] intervals) {

        int len;
        if(intervals == null || (len = intervals.length) == 0) {
            return intervals;
        }

        List<Integer> leftAxis = new ArrayList<>();
        List<Integer> rhtAxis = new ArrayList<>();

        for(int i = 0; i < len; i++) {
            leftAxis.add(intervals[i][0]);
            rhtAxis.add(intervals[i][1]);
        }

        Collections.sort(leftAxis);
        Collections.sort(rhtAxis);

        boolean[] merge = new boolean[len];
        int count = len;

        for(int i = len - 1; i > 0; i--) {
            if(leftAxis.get(i) <= rhtAxis.get(i - 1)) {
                // 合并
                rhtAxis.set(i - 1, rhtAxis.get(i));
                merge[i] = true;
                count--;
            }
        }

        int[][] res = new int[count][2];
        int idx = 0;
        for(int i = 0; i < len; i++) {
            if(!merge[i]) {
                res[idx][0] = leftAxis.get(i);
                res[idx][1] = rhtAxis.get(i);
                idx++;
            }

        }

        return res;

    }

    /**
     * 更好版本
     * 7ms 91%
     */
    public int[][] merge2(int[][] intervals) {

        int len;
        if(intervals == null || (len = intervals.length) == 0) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });

        int[][] res = new int[len][2];
        int idx = -1;
        for(int[] interval : intervals) {
            if(idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 合并
                res[idx][1] = Math.max(interval[1], res[idx][1]);
            }
        }

        return Arrays.copyOf(res, idx + 1);
    }
}
