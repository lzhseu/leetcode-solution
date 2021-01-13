package leetcode.算法.贪心;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lzh
 * @date 2020/12/15 13:34
 */
public class 用最少数量的箭引爆气球_452 {


    public int findMinArrowShots(int[][] points) {

        if(points.length == 0) {
            return 0;
        }

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return Integer.compare(a1[1], a2[1]);
            }
        });

        int end = points[0][1];
        int count = 1;
        for(int i = 1; i < points.length; i++) {
            // 下一个的 start <= 上一个的 end，则可以用一支箭
            if(points[i][0] <= end) {
                continue;
            }
            end = points[i][1];
            count++;
        }

        return count;
    }

    @Test
    public void test() {
        int res = findMinArrowShots(new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}});
        System.out.println(res);
    }
}
