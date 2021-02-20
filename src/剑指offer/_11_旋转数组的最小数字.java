package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/13 18:31
 */
public class _11_旋转数组的最小数字 {

    /**
     * 用二分查找，一步步缩小查找的范围
     */

    /**
     * 做法1
     * 1 ms 28.08%    38.4 MB 27.76%
     */
    public int minArray1(int[] numbers) {

        int left = 0, right = numbers.length - 1;
        int mid = 0;

        while(numbers[left] >= numbers[right]) {

            if(right - left <= 1) {
                mid = right;
                break;
            }

            mid = ((right - left) >> 1) + left;

            if(numbers[left] == numbers[mid]) {
                left++;
                mid = left;
                continue;
            }

            if(numbers[left] <= numbers[mid]) {
                // 左边有序
                left = mid;
            } else if(numbers[mid] <= numbers[right]) {
                // 右边有序
                right = mid;
            }
        }

        return numbers[mid];
    }


    /**
     * 做法2
     * 0 ms 100.00%    38.3 MB 42.24%
     */
    public int minArray(int[] numbers) {

        int left = 0, right = numbers.length - 1;

        while(left < right - 1) {

            int mid = left + ((right - left) >> 1);

            if(numbers[left] == numbers[mid]) {
                left++;
            } else if(numbers[left] < numbers[mid]) {
                // 左边有序
                if(numbers[left] < numbers[right]) {
                    // 整体有序
                    return numbers[left];
                } else {
                    left = mid + 1;
                }

            } else if(numbers[mid] <= numbers[right]) {
                // 右边有序
                right = mid;
            }
        }

        return Math.min(numbers[left], numbers[right]);
    }
}
