package leetcode.数据结构.数组;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/7/22 9:55
 */
public class 旋转数组的最小数字_154 {
    public int minArray0(int[] numbers) {

        if(numbers == null || numbers.length == 0) {
            throw new RuntimeException("数组为空");
        }

        int left = 0, right = numbers.length - 1;
        int mid;

        while(left < right - 1) {
            mid = left + ((right - left) >> 1);

            if(numbers[left] == numbers[mid]) {
                left++;
            } else if(numbers[left] < numbers[mid]) {
                if(numbers[left] < numbers[right]) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                right = mid;
            }

        }
        return Math.min(numbers[left], numbers[right]);
    }

    public int minArray(int[] numbers) {

        if(numbers == null || numbers.length == 0) {
            throw new RuntimeException("Invalid input!");
        }

        int left = 0, right = numbers.length - 1, mid = left;

        if(numbers[left] < numbers[right]) {
            return numbers[left];
        }

        while(left < right-1) {
            mid = left + ((right - left) >> 1);
            if(numbers[left] == numbers[mid] && numbers[mid] == numbers[right]) {
                return sequentialSearch(left, right, numbers);
            }
            if(numbers[left] <= numbers[mid]) {
                left = mid;
            } else if(numbers[mid] <= numbers[right]) {
                right = mid;
            }
        }
        return numbers[right];
    }

    private int sequentialSearch(int left, int right, int[] numbers) {
        int res = numbers[left];
        for(int i = left+1; i < right+1; i++) {
            if(numbers[i] < res) {
                res = numbers[i];
            }
        }
        return res;
    }

    @Test
    public void test() {

        int[] a = new int[] {3,4,5,1,2};
        System.out.println(minArray(a));

        a = new int[]{2, 2, 2, 0, 1};
        System.out.println(minArray(a));

        a = new int[]{2, 2, 2, 1, 2};
        System.out.println(minArray(a));
        
    }
}
