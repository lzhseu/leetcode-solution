package leetcode.算法.DFS_BFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/26 12:17
 */
public class 被围绕的区域_130 {

    /**
     * 递归 DFS
     */
    public void solve(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }

        int m = board.length, n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                boolean isEdge = i == 0 || j == 0 || i == m-1 || j == n-1;
                if(isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if(i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfs(board, i-1, j);  //up
        dfs(board, i, j-1);  //left
        dfs(board, i+1, j);  //down
        dfs(board, i, j+1);  //right
    }

    /**
     * 递归栈
     */
    private static class Pos {
        private int i;
        private int j;
        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public void solve2(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }

        int m = board.length, n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                boolean isEdge = i == 0 || j == 0 || i == m-1 || j == n-1;
                if(isEdge && board[i][j] == 'O') {
                    dfs_stack(board, i, j);
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs_stack(char[][] board, int i, int j) {

        Deque<Pos> stack = new LinkedList<>();
        stack.push(new Pos(i, j));
        board[i][j] = '#';

        while(!stack.isEmpty()) {

            Pos curr = stack.peek();

            // up
            if(curr.i-1 >= 0 && board[curr.i-1][curr.j] == 'O') {
                board[curr.i-1][curr.j] = '#';
                stack.push(new Pos(curr.i-1, curr.j));
                continue;
            }

            // down
            if(curr.i+1 < board.length && board[curr.i+1][curr.j] == 'O') {
                board[curr.i+1][curr.j] = '#';
                stack.push(new Pos(curr.i+1, curr.j));
                continue;
            }

            // left
            if(curr.j-1 >= 0 && board[curr.i][curr.j-1] == 'O') {
                board[curr.i][curr.j-1] = '#';
                stack.push(new Pos(curr.i, curr.j-1));
                continue;
            }

            // right
            if(curr.j+1 < board[0].length && board[curr.i][curr.j+1] == 'O') {
                board[curr.i][curr.j+1] = '#';
                stack.push(new Pos(curr.i, curr.j+1));
                continue;
            }

            stack.pop();
        }
    }

    /**
     * BFS
     */
    public void solve_bfs(char[][] board) {
        if(board == null || board.length == 0) {
            return;
        }

        int m = board.length, n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                boolean isEdge = i == 0 || j == 0 || i == m-1 || j == n-1;
                if(isEdge && board[i][j] == 'O') {
                    bfs(board, i, j);
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void bfs(char[][] board, int i, int j) {

        Deque<Pos> queue = new LinkedList<>();
        queue.add(new Pos(i, j));
        board[i][j] = '#';

        while(!queue.isEmpty()) {

            Pos curr = queue.remove();

            // up
            if(curr.i-1 >= 0 && board[curr.i-1][curr.j] == 'O') {
                board[curr.i-1][curr.j] = '#';
                queue.add(new Pos(curr.i-1, curr.j));
            }

            // down
            if(curr.i+1 < board.length && board[curr.i+1][curr.j] == 'O') {
                board[curr.i+1][curr.j] = '#';
                queue.add(new Pos(curr.i+1, curr.j));
            }

            // left
            if(curr.j-1 >= 0 && board[curr.i][curr.j-1] == 'O') {
                board[curr.i][curr.j-1] = '#';
                queue.add(new Pos(curr.i, curr.j-1));
            }

            // right
            if(curr.j+1 < board[0].length && board[curr.i][curr.j+1] == 'O') {
                board[curr.i][curr.j+1] = '#';
                queue.add(new Pos(curr.i, curr.j+1));
            }

        }
    }

    /**
     * 并查集
     */
    public void solve_uf(char[][] board) {
        // 用并查集来做

        if(board == null || board.length == 0) {
            return;
        }

        int m = board.length, n = board[0].length;

        // 给 dummy 留一个额外位置
        UnionFind UF = new UnionFind(m * n + 1);
        int dummy = m * n;

        // 将首列和末列的 O 与 dummy 连通
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                UF.union(dummy, i * n);
            }
            if(board[i][n - 1] == 'O') {
                UF.union(dummy, i * n + n - 1);
            }
        }

        // 将首行和末行的 O 与 dummy 连通
        for(int i = 0; i < n; i++) {
            if(board[0][i] == 'O') {
                UF.union(dummy, i);
            }
            if(board[m - 1][i] == 'O') {
                UF.union(dummy, (m - 1) * n + i);
            }
        }

        // 方向数组 d 是上下左右搜索的常用手法
        int[][] d = new int[][] {{1,0}, {0,1}, {0,-1}, {-1,0}};
        for(int i = 1; i < m - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                if(board[i][j] == 'O') {
                    // 将此 O 与上下左右的 'O' 连通
                    for(int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if(board[x][y] == 'O') {
                            UF.union(i * n + j, x * n + y);
                        }
                    }
                }
            }
        }

        // 所有不和 dummy 连通的 O，都要被替换
        for(int i = 1; i < m - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                if(!UF.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }

    }

    static class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) {
                return false;
            }
            parent[rootY] = rootX;
            return true;
        }

        public int find(int x) {
            while(x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

    }
}
