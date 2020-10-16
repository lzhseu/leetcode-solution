package leetcode.算法.双指针;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/8/20 11:15
 */
public class 盛最多水的容器_11 {

    public int maxArea(int[] height) {

        int left = 0, right = height.length - 1;
        int max = 0;

        while(left < right) {
            int tmp = (right - left) * Math.min(height[left], height[right]);
            if(max < tmp) {
                max = tmp;
            }
            if(height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    @Test
    public void test() {
        int[] height = {2,3,4,5,18,17,6};
        int area = maxArea(height);
        System.out.println(area);
    }
}
