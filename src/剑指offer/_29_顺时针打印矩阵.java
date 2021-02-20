package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/16 10:37
 */
public class _29_顺时针打印矩阵 {

    /**
     * 细节是魔鬼
     */
    public int[] spiralOrder(int[][] matrix) {

        int rows, cols;
        if((rows = matrix.length) == 0 || (cols = matrix[0].length) == 0) {
            return new int[] {};
        }

        int[] result = new int[rows * cols];
        int index = 0;

        for(int i = 0; i <= (Math.min(rows, cols) - 1) / 2; i++) {

            int start  = i;
            int colEnd = cols - 1 - start;
            int rowEnd = rows - 1 - start;

            // 从左到右
            for(int j = start; j <= colEnd; j++) {
                //System.out.println("[" + start + ", " + j + "]");
                result[index++] = matrix[start][j];
            }

            // 从上到下
            if(start < rowEnd) {
                for(int j = start + 1; j <= rowEnd; j++) {
                    result[index++] = matrix[j][colEnd];
                }
            }


            // 从右到左
            if(start < rowEnd) {
                for(int j = colEnd - 1; j >= start; j--) {
                    result[index++] = matrix[rowEnd][j];
                }
            }


            // 从下往上
            if(start < colEnd) {
                for(int j = rowEnd - 1; j > start; j--) {
                    result[index++] = matrix[j][start];
                }
            }

        }

        return result;
    }
}
