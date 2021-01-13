package leetcode.算法.双指针;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/12/14 18:34
 */
public class 区间列表的交集_986 {

    /**
     * 愚蠢的编码
     * 4 ms 91.87%    39.5 MB 66.08%
     */
    public int[][] intervalIntersection1(int[][] A, int[][] B) {

        int lenA = A.length, lenB = B.length;
        if(lenA == 0 || lenB == 0) {
            return new int[][] {};
        }

        int[][] result = new int[lenA + lenB][2];
        int[] sortArr = new int[4];
        int pa = 0, pb = 0, count = 0;

        while(pa < lenA && pb < lenB) {

            int[] a = A[pa];
            int[] b = B[pb];

            // 这种情况肯定没有交集
            if(a[1] < b[0]) {
                pa++;
                continue;
            } else if(b[1] < a[0]) {
                pb++;
                continue;
            }

            // 对四个坐标进行归并排序，取中间两个
            int pta = 0, ptb = 0;
            for(int i = 0; i < 4; i++) {
                if(pta == 2) {
                    sortArr[i] = b[ptb++];
                } else if(ptb == 2) {
                    sortArr[i] = a[pta++];
                } else {
                    sortArr[i] = a[pta] <= b[ptb] ? a[pta++] : b[ptb++];
                }

            }

            // 取出结果赋值
            result[count][0] = sortArr[1];
            result[count][1] = sortArr[2];
            count++;

            // 判断丢弃哪一个
            if(sortArr[2] == a[1]) {
                pa++;
            } else {
                pb++;
            }
        }

        return Arrays.copyOf(result, count);
    }

    /**
     * 优雅的编码
     * 时间和空间和上面一样
     */
    public int[][] intervalIntersection2(int[][] A, int[][] B) {

        int lenA = A.length, lenB = B.length;
        if(lenA == 0 || lenB == 0) {
            return new int[][] {};
        }

        int[][] result = new int[lenA + lenB][2];

        int pa = 0, pb = 0, count = 0;

        while(pa < lenA && pb < lenB) {

            int lo = Math.max(A[pa][0], B[pb][0]);
            int hi = Math.min(A[pa][1], B[pb][1]);
            if(lo <= hi) {
                result[count][0] = lo;
                result[count++][1] = hi;
            }

            if(A[pa][1] < B[pb][1]) {
                pa++;
            } else {
                pb++;
            }
        }

        return Arrays.copyOf(result, count);
    }
}
