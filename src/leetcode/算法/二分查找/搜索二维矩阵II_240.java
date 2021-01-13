package leetcode.算法.二分查找;

/**
 * @author lzh
 * @date 2020/12/24 15:55
 */
public class 搜索二维矩阵II_240 {

    /**
     * 朴素二分
     * 8 ms 29.91%    44 MB 70.78%
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i = 0; i < rows; i++) {
            if(matrix[i][0] <= target && matrix[i][cols - 1] >= target) {
                if(binarySearch(matrix[i], target)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean binarySearch(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len;

        while(left < right) {

            int mid = left + ((right - left) >> 1);

            if(nums[mid] == target) {
                return true;
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return false;
    }

    /**
     * 消消乐，记得剑指 offer 有这道题
     * 5 ms 99.01%    43.7 MB 93.75%
     */
    public boolean searchMatrix2(int[][] matrix, int target) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int i = 0, j = cols - 1;

        while(i < rows && j >= 0) {
            if(matrix[i][j] == target) {
                return true;
            } else if(matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }
}
