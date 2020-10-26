package leetcode.算法.双指针;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/10/19 9:38
 */
public class 接雨水_42 {

    /**
     * 自己最初的版本，双指针，编码不优雅
     * 3ms 38.14%    38.3M 93.20%
     */
    public int trap1(int[] height) {

        int len;
        if(height == null || (len = height.length) < 3) {
            return 0;
        }

        int p1 = 0, p2 = 1;

        int area = 0;

        while(p1 < len - 2) {

            int h1 = height[p1];
            int h2 = height[p2];

            int tmpHigh = height[p2];
            int tmpHIdx = p2;
            int tmpLow = height[p2];

            if(h1 <= h2) {
                p1++;
                p2++;
                continue;
            } else {
                // 直到越界或者 h2 >= h1
                // 这个过程需要记录次高点
                 while(++p2 < len && height[p2] < h1){
                     if (tmpHigh < height[p2]) {
                         tmpHigh = height[p2];
                         tmpHIdx = p2;
                     }
                     if (tmpLow > height[p2]) {
                         tmpLow = height[p2];
                     }
                 }

            }

            // 如果 p2 已经越界了
            if(p2 >= len) {

                if(tmpHigh > tmpLow) {
                    p2 = tmpHIdx;
                } else {
                    p1++;
                    p2 = p1 + 1;
                    continue;
                }

            }

            // 如果 p2 没有越界
            int whole = (p2 - p1 - 1) * Math.min(h1, height[p2]);
            while(++p1 < p2) {
                whole -= height[p1];
            }
            area += whole;
            p2 = p1 + 1;
        }

        return area;
    }


    /////////////////// 以下是参考官方的四种解法 //////////////////////

    /**
     * 按列求
     * 时间复杂度 O(n^2) 空间复杂度 O(1)
     * 77ms 12.85%    37.9M 99.52%
     */
    public int trap2(int[] height) {

        int len;
        if(height == null || (len = height.length) < 3) {
            return 0;
        }

        int area = 0;

        // 对每个列求
        for (int i = 1; i < len - 1; i++) {

            int max_left = 0, max_right = 0;

            // 求左边最高点
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }

            // 求右边最高点
            for (int j = i + 1; j < len; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }

            int min = Math.min(max_left, max_right);
            if (min > height[i]) {
                area += min - height[i];
            }
        }

        return area;
    }


    /**
     * 动态规划
     * 按列求时对每个元素都需要向左和向右遍历一遍，所以考虑用两个数组来记录每个元素最左边和最右边的最大值
     * 时间复杂度 O(n) 空间复杂度 O(n)
     * 1ms 99.99%    37.4M 100%
     */
    public int trap3(int[] height) {

        int len;
        if(height == null || (len = height.length) < 3) {
            return 0;
        }

        int area = 0;

        // max_left[i] 表示位置 i 的左边的最大的元素，不包括 i
        int[] max_left = new int[len];
        int[] max_right = new int[len];

        for (int i = 1; i < len - 1; i++) {
            max_left[i] = Math.max(height[i - 1], max_left[i - 1]);
        }

        for (int i = len - 2; i >= 0; i--) {
            max_right[i] = Math.max(height[i + 1], max_right[i + 1]);
        }

        for (int i = 1; i < len - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                area += min - height[i];
            }
        }

        return area;
    }


    /**
     * 双指针，这个解法妙啊
     * 时间复杂度 O(n) 空间复杂度 O(1)
     * 1ms 99.99%
     */
    public int trap4(int[] height) {
        int len;
        if(height == null || (len = height.length) < 3) {
            return 0;
        }

        int area = 0;

        int lp = 1, rp = len - 2;
        int max_left = 0, max_right = 0;

        for (int i = 1; i < len - 1; i++) {

            // 从左往右更新
            if (height[lp - 1] < height[rp + 1]) {

                max_left = Math.max(max_left, height[lp - 1]);
                int min = max_left;
                if (min > height[lp]) {
                    area += min - height[lp];
                }
                lp++;

            } else {

                max_right = Math.max(max_right, height[rp + 1]);
                int min = max_right;
                if (min > height[rp]) {
                    area += min - height[rp];
                }
                rp--;
            }

        }

        return area;
    }


    /**
     * 栈
     */
    public int trap5(int[] height) {
        return 0;
    }



    @Test
    public void test() {

        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] heights2 = {4, 2, 3};
        System.out.println(trap4(heights));
        System.out.println(trap4(heights2));
    }

}
