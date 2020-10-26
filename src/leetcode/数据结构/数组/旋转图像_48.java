package leetcode.数据结构.数组;

/**
 * @author lzh
 * @date 2020/10/20 16:33
 */
public class 旋转图像_48 {

    /**
     * 0ms 100%    38.4M 98.21%
     */
    public void rotate(int[][] matrix) {

        int n = matrix.length;

        int layers = ((n + 1) >> 1);

        // 从外到内按层遍历
        for(int i = 0; i < layers; i++) {

            for(int j = i; j <= n - 2 - i; j++) {

                // 规律：(i,j) -> (j, n-1-i)
                //      (i, j) = (n-1-j, i)

                // int first = matrix[i][j];
                // int x = i, y = j;

                // for(int k = 0; k < 4; k++) {

                //     matrix[x][y] = matrix[n - 1 - y][x];
                //     int tmp = x;
                //     x = n - 1 - y;
                //     y = tmp;
                // }
                // matrix[j][n-1-i] = first;


                int first = matrix[i][j];
                int x = i, y = j;
                while(true) {
                    matrix[x][y] = matrix[n - 1 - y][x];
                    int tmp = x;
                    x = n - 1 - y;
                    y = tmp;
                    if(x == i && y == j) {
                        matrix[j][n-1-i] = first;
                        break;
                    }
                }

            }
        }
    }
}
