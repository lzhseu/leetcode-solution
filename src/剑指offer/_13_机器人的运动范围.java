package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/13 20:06
 */
public class _13_机器人的运动范围 {


    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return trace2(m, n, 0, 0, k, visited);
    }

    /**
     * 写法1
     */
    private int trace(int m, int n, int ridx, int cidx, int k, boolean[][] visited) {

        // base case
        if(!canIn(ridx, cidx, k)) {
            return 0;
        }

        // 能进来此方格

        int count = 1;

        visited[ridx][cidx] = true;

        // go up
        if(ridx - 1 >= 0 && !visited[ridx - 1][cidx]) {
            count += trace(m, n, ridx - 1, cidx, k, visited);
        }

        // go down
        if(ridx + 1 < m && !visited[ridx + 1][cidx]) {
            count += trace(m, n, ridx + 1, cidx, k, visited);
        }

        // go left
        if(cidx - 1 >= 0 && !visited[ridx][cidx - 1]) {
            count += trace(m, n, ridx, cidx - 1, k, visited);
        }

        // go right
        if(cidx + 1 < n && !visited[ridx][cidx + 1]) {
            count += trace(m, n, ridx, cidx + 1, k, visited);
        }

        return count;
    }


    /**
     * 写法2
     */
    private int trace2(int m, int n, int ridx, int cidx, int k, boolean[][] visited) {

        // base case
        if(ridx < 0 || ridx >= m ||
                cidx < 0 || cidx >= n ||
                visited[ridx][cidx] || !canIn(ridx, cidx, k)) {
            return 0;
        }

        // 能进来此方格

        visited[ridx][cidx] = true;

        return 1 + trace2(m, n, ridx - 1, cidx, k, visited) +
                trace2(m, n, ridx + 1, cidx, k, visited) +
                trace2(m, n, ridx, cidx - 1, k, visited) +
                trace2(m, n, ridx, cidx + 1, k, visited);

    }


    private boolean canIn(int ridx, int cidx, int threshold) {

        return getSum(ridx) + getSum(cidx) <= threshold;
    }


    private int getSum(int number) {

        int sum = 0;

        while(number > 0) {
            sum += (number % 10);
            number /= 10;
        }

        return sum;
    }
}
