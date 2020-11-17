package leetcode.数据结构.数组;

import org.junit.Test;

import java.util.*;

/**
 * @author lzh
 * @date 2020/11/17 9:00
 */
public class 距离顺序排列矩阵单元格_1030 {

    /**
     * 解法1：使用优先级队列
     * O(NlogN) + O(N)
     * 31 ms 10.29%    40.4 MB 93.89%
     */
    public int[][] allCellsDistOrder1(int R, int C, int r0, int c0) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0) - Math.abs(o2[0] - r0) - Math.abs(o2[1] - c0));

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                pq.add(new int[] {i, j});
            }
        }

        int[][] res = new int[R * C][2];
        int i = 0;
        while(!pq.isEmpty()) {
            res[i++] = pq.poll();
        }
        return res;
    }

    /**
     * 解法2:先存储结果再排序
     * 17 ms 54.56%    40.1 MB 99.41%
     */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {

        // 解法二：先存储结果，再排序
        int[][] res = new int[R * C][2];

        int k = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                res[k++] = new int[] {i, j};
            }
        }

        Arrays.sort(res, (o1, o2) -> Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0) - Math.abs(o2[0] - r0) - Math.abs(o2[1] - c0));

        return res;
    }


    /**
     * 解法3：BFS
     * 18 ms 44.47%    40.3 MB 96.25%
     * 这是框架式代码，所以时间稍多
     * 编码还是差一点，别人的 BFS 只要 6ms
     * 我觉得算法慢的主要原因是不断地 new int[] {r, c}
     * 还有可能就是容器的 offer 和 poll 比直接读写数组慢
     */
    public int[][] allCellsDistOrder3_1(int R, int C, int r0, int c0) {

        // 解法三：BFS
        int[][] res = new int[R * C][2];
        int index = 0;
        boolean[][] visited = new boolean[R][C];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r0, c0});
        visited[r0][c0] = true;

        while(!queue.isEmpty()) {
            int[] first = queue.poll();
            int r = first[0];
            int c = first[1];
            res[index++] = first;

            if(r - 1 >= 0 && !visited[r - 1][c]) {
                queue.offer(new int[] {r - 1, c});
                visited[r - 1][c] = true;
            }
            if(r + 1 < R && !visited[r + 1][c]) {
                queue.offer(new int[] {r + 1, c});
                visited[r + 1][c] = true;
            }
            if(c - 1 >= 0 && !visited[r][c - 1]) {
                queue.offer(new int[] {r, c - 1});
                visited[r][c - 1] = true;
            }
            if(c + 1 < C && !visited[r][c + 1]) {
                queue.offer(new int[] {r, c + 1});
                visited[r][c + 1] = true;
            }
        }

        return res;
    }

    /**
     * BFS 快速版本
     * 6ms 96.89%
     */
    public int[][] allCellsDistOrder3_2(int R, int C, int r0, int c0) {

        // 解法三：BFS
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int[][] res = new int[R * C][2];
        boolean[][] visited = new boolean[R][C];
        res[0][0] = r0;
        res[0][1] = c0;
        visited[r0][c0] = true;

        int count = 1;
        int begin = 0, end = 1;

        while(count < R * C) {

            int sz = 0;

            for(int i = begin; i < end; i++) {

                int r = res[i][0], c = res[i][1];

                for(int j = 0; j < 4; j++) {

                    int newR = r + dx[j], newC = c + dy[j];
                    if(newR >= 0 && newR < R && newC >= 0 && newC < C && !visited[newR][newC]) {
                        res[count][0] = newR;
                        res[count][1] = newC;
                        visited[newR][newC] = true;
                        count++;
                        sz++;
                    }
                }
            }

            begin = end;
            end += sz;
        }

        return res;
    }

    /**
     * 解法4：桶排序
     */
    public int[][] allCellsDistOrder4(int R, int C, int r0, int c0) {

        // 桶排序
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        List<List<int[]>> bucket = new ArrayList<List<int[]>>();
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<int[]>());
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int d = dist(i, j, r0, c0);
                bucket.get(d).add(new int[]{i, j});
            }
        }
        int[][] ret = new int[R * C][];
        int index = 0;
        for (int i = 0; i <= maxDist; i++) {
            for (int[] it : bucket.get(i)) {
                ret[index++] = it;
            }
        }
        return ret;
    }

    public int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }


    @Test
    public void test() {
        allCellsDistOrder4(2, 3, 1, 2);
    }
}
