package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/13 10:44
 */
public class _04_二维数组中的查找 {

    /**
     * 找到规律，不断缩小查找的范围。也可以属于一种贪心吧
     * 0 ms 100.00%    44.3 MB 35.58%
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        int rows, cols;
        if((rows = matrix.length) == 0 || (cols = matrix[0].length) == 0) {
            return false;
        }

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
